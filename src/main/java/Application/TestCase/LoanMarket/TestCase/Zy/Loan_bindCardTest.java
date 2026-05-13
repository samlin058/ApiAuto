package Application.TestCase.LoanMarket.TestCase.Zy;

import Application.DataFactory.LoanMarket.H5ProcessContext;
import Application.DataFactory.LoanMarket.XYJH5Process;
import Data.Apis.LoanMarket.CardController;
import Data.Ro.LoanMarket.CardControllerBindCardApplyRo;
import Data.Ro.LoanMarket.CardControllerInitBindCardTaskRo;
import Data.Ro.LoanMarket.CardControllerSupportListRo;
import Data.Ro.LoanMarket.CardControllerVerifyCardRo;
import Service.Base.BaseTestCase2;
import Service.Enum.DatabaseEnum;
import Service.Util.variable.BankCardHandler;
import Service.Util.Universal.JDBCHandler;
import Service.Util.Universal.JsonHandler;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static Application.DataFactory.LoanMarket.XYJH5Process.H5processByPartner;


/**
 * 绑卡测试类
 * 测试借款和还款过程中的银行卡绑定功能
 */
public class Loan_bindCardTest extends BaseTestCase2 {

    // 常量定义
    private static final CardController CARD_CONTROLLER = new CardController();
    private static final String DEFAULT_CHANNEL_CODE = "luandaoTest2";
    private static final String DEFAULT_VERIFY_CODE = "111111";
    private static final String ALTERNATIVE_VERIFY_CODE = "888888";
    private static final String LOAN_BIND_TYPE = "loan";
    private static final String REPAYMENT_BIND_TYPE = "repayment";
    private static final long CARD_VERIFICATION_TIMEOUT_MS = 8000L;
    private static final long CARD_VERIFICATION_INTERVAL_MS = 200L;

    // 支持的资方代码
    private static final String PARTNER_NIWODAI = "NIWODAI";
    private static final String PARTNER_HUOSHANRONG = "HUOSHANRONG";
    private static final String PARTNER_HALUO = "HALUO";

    // ==================== 正向用例 ====================

    @Test(testName = "借款过程中绑卡，一次短验：绑卡成功", dataProvider = "positiveTestData", enabled = false)
    public static void TC_Bind_01(String verifyCode, String partnerCode, String cardBindType) {
        executeBindCardTest(verifyCode, partnerCode, cardBindType, 1, true);
    }

    @Test(testName = "还款过程中绑卡：一次短验绑卡成功", dataProvider = "positiveTestData", enabled = false)
    public static void TC_Bind_02(String verifyCode, String partnerCode, String cardBindType) {
        executeBindCardTest(verifyCode, partnerCode, cardBindType, 1, true);
    }

    @Test(testName = "借款过程中绑卡，需要二次短验：绑卡成功", dataProvider = "positiveTestData", enabled = false)
    public static void TC_Bind_03(String verifyCode, String partnerCode, String cardBindType) {
        executeBindCardTest(verifyCode, partnerCode, cardBindType, 2, true);
    }

    @Test(testName = "还款过程中绑卡，需要二次短验：绑卡成功", dataProvider = "positiveTestData", enabled = false)
    public static void TC_Bind_04(String verifyCode, String partnerCode, String cardBindType) {
        executeBindCardTest(verifyCode, partnerCode, cardBindType, 2, true);
    }

    // ==================== 反向用例 ====================

    @Test(testName = "借款过程中绑卡,不支持的卡bin：绑卡失败", dataProvider = "negativeTestData")
    public static void TC_Bind_05(String partnerCode, String cardBindType) {
        executeBindCardTest(null, partnerCode, cardBindType, 1, false);
    }

    @Test(testName = "还款过程中绑卡，不支持的卡bin：绑卡失败", dataProvider = "negativeTestData")
    public static void TC_Bind_06(String partnerCode, String cardBindType) {
        executeBindCardTest(null, partnerCode, cardBindType, 1, false);
    }

    @Test(testName = "输入无效的银行卡号：绑卡失败")
    public static void TC_Bind_07() {
        H5ProcessContext context = createH5ProcessContext(PARTNER_NIWODAI);
        String invalidCardNum = "1234567890123456789"; // 无效的银行卡号

        try {
            CARD_CONTROLLER.initBindCardTask(
                    new CardControllerInitBindCardTaskRo(context.getMobile(), context.getProductCode(), invalidCardNum, LOAN_BIND_TYPE));
            Assert.fail("应该抛出异常，因为银行卡号无效");
        } catch (Exception e) {
            // 期望的异常，测试通过
            System.out.println("预期的异常: " + e.getMessage());
        }
    }

    @Test(testName = "输入验证码错误：绑卡失败")
    public static void TC_Bind_08() {
        H5ProcessContext context = createH5ProcessContext(PARTNER_NIWODAI);
        String cardNum = generateSupportedCardNumber(context, PARTNER_NIWODAI);
        String wrongVerifyCode = "999999"; // 错误的验证码

        try {
            String initResult = CARD_CONTROLLER.initBindCardTask(
                    new CardControllerInitBindCardTaskRo(context.getMobile(), context.getProductCode(), cardNum, LOAN_BIND_TYPE));
            String taskId = JsonHandler.readStringByJsonPath("$.data.taskId", initResult);

            CARD_CONTROLLER.bindCardApply(new CardControllerBindCardApplyRo(context.getMobile(), context.getProductCode(), cardNum, taskId));
            CARD_CONTROLLER.verifyCard(new CardControllerVerifyCardRo(taskId, wrongVerifyCode));

            Assert.fail("应该抛出异常，因为验证码错误");
        } catch (Exception e) {
            // 期望的异常，测试通过
            System.out.println("预期的异常: " + e.getMessage());
        }
    }

    @Test(testName = "重复绑定同一张卡：绑卡失败")
    public static void TC_Bind_09() {
        H5ProcessContext context = createH5ProcessContext(PARTNER_NIWODAI);
        String cardNum = generateSupportedCardNumber(context, PARTNER_NIWODAI);

        // 第一次绑卡
        executeBindCardFlow(context, cardNum, DEFAULT_VERIFY_CODE, LOAN_BIND_TYPE, 1);

        // 尝试重复绑卡
        try {
            CARD_CONTROLLER.initBindCardTask(new CardControllerInitBindCardTaskRo(context.getMobile(), context.getProductCode(), cardNum, LOAN_BIND_TYPE));
            Assert.fail("应该抛出异常，因为重复绑定同一张卡");
        } catch (Exception e) {
            // 期望的异常，测试通过
            System.out.println("预期的异常: " + e.getMessage());
        }
    }

    // ==================== 辅助方法 ====================

    /**
     * 执行绑卡测试的通用方法
     *
     * @param verifyCode       验证码
     * @param partnerCode      资方代码
     * @param cardBindType     绑卡类型
     * @param verifyTimes      验证次数
     * @param useSupportedCard 是否使用支持的银行卡
     */
    private static void executeBindCardTest(String verifyCode, String partnerCode, String cardBindType, int verifyTimes, boolean useSupportedCard) {
        H5ProcessContext context = createH5ProcessContext(partnerCode);
        String cardNum = useSupportedCard ?
                generateSupportedCardNumber(context, partnerCode) :
                generateUnsupportedCardNumber(context, partnerCode);

        if (useSupportedCard) {
            executeBindCardFlow(context, cardNum, verifyCode, cardBindType, verifyTimes);
        } else {
            executeUnsupportedCardFlow(context, cardNum, cardBindType);
        }
    }

    /**
     * 创建H5流程上下文
     */
    private static H5ProcessContext createH5ProcessContext(String partnerCode) {
        return H5processByPartner(DEFAULT_CHANNEL_CODE, partnerCode, XYJH5Process.H5ProcessStage.CREDIT_APPLY);
    }

    /**
     * 生成支持的银行卡号
     */
    private static String generateSupportedCardNumber(H5ProcessContext context, String partnerCode) {
        String supportCardList = CARD_CONTROLLER.supportList(new CardControllerSupportListRo(partnerCode, context.getUid()));
        List<String> supportCardListStr = JsonHandler.readStringListByJsonPath("$.data.bankName", supportCardList);
        return BankCardHandler.generate19DigitCardNumberInSupportedBanks(supportCardListStr);
    }

    /**
     * 生成不支持的银行卡号
     */
    private static String generateUnsupportedCardNumber(H5ProcessContext context, String partnerCode) {
        String supportCardList = CARD_CONTROLLER.supportList(new CardControllerSupportListRo(partnerCode, context.getUid()));
        List<String> supportCardListStr = JsonHandler.readStringListByJsonPath("$.data.bankName", supportCardList);
        return BankCardHandler.generate19DigitCardNumberNotInSupportedBanks(supportCardListStr);
    }

    /**
     * 执行支持的银行卡绑卡流程
     */
    private static void executeBindCardFlow(H5ProcessContext context, String cardNum,
                                            String verifyCode, String cardBindType, int verifyTimes) {
        String initResult = CARD_CONTROLLER.initBindCardTask(
                new CardControllerInitBindCardTaskRo(context.getMobile(), context.getProductCode(), cardNum, cardBindType));
        String taskId = JsonHandler.readStringByJsonPath("$.data.taskId", initResult);

        validateTaskId(taskId);

        for (int i = 0; i < verifyTimes; i++) {
            CARD_CONTROLLER.bindCardApply(
                    new CardControllerBindCardApplyRo(context.getMobile(), context.getProductCode(), cardNum, taskId));
            CARD_CONTROLLER.verifyCard(new CardControllerVerifyCardRo(taskId, verifyCode));
        }

        assertUserBoundCardEqualsWithPolling(context.getUid(), cardNum, CARD_VERIFICATION_TIMEOUT_MS, CARD_VERIFICATION_INTERVAL_MS);
    }

    /**
     * 执行不支持的银行卡绑卡流程
     */
    private static void executeUnsupportedCardFlow(H5ProcessContext context, String cardNum, String cardBindType) {
        String initResult = CARD_CONTROLLER.initBindCardTask(
                new CardControllerInitBindCardTaskRo(context.getMobile(), context.getProductCode(), cardNum, cardBindType));
        String taskId = JsonHandler.readStringByJsonPath("$.data.taskId", initResult);
        CARD_CONTROLLER.bindCardApply(
                new CardControllerBindCardApplyRo(context.getMobile(), context.getProductCode(), cardNum, taskId));
    }

    /**
     * 验证任务ID
     */
    private static void validateTaskId(String taskId) {
        Assert.assertNotNull(taskId, "taskId should not be null");
        Assert.assertFalse("".equals(taskId), "taskId should not be empty");
    }

    /**
     * 轮询验证用户绑定的银行卡号
     *
     * @param uid            用户ID
     * @param expectedCardNo 期望的银行卡号
     * @param timeoutMs      超时时间（毫秒）
     * @param intervalMs     轮询间隔（毫秒）
     */
    private static void assertUserBoundCardEqualsWithPolling(String uid, String expectedCardNo, long timeoutMs, long intervalMs) {
        long start = System.currentTimeMillis();
        String lastCard = null;

        while (System.currentTimeMillis() - start <= timeoutMs) {
            try {
                String cardJson = JDBCHandler.queryFirstRowAsJson(DatabaseEnum.LoanMarkest,
                        "select card_no from tbl_user_card where uid =?", uid);
                if (cardJson != null) {
                    lastCard = JsonHandler.readStringByJsonPath("$.card_no", cardJson);
                    if (expectedCardNo.equals(lastCard)) {
                        Assert.assertEquals(lastCard, expectedCardNo, "Bound card number should match expected value");
                        return;
                    }
                }
                Thread.sleep(intervalMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                System.err.println("Error during card verification polling: " + e.getMessage());
                // 继续轮询，不中断测试
            }
        }
        Assert.fail("Timed out waiting for expected bound card. lastCard=" + lastCard + ", expected=" + expectedCardNo);
    }

    // ==================== 数据提供者 ====================

    /**
     * 正向测试数据提供者
     * 包含所有资方的借款和还款绑卡测试数据
     */
    @DataProvider(name = "positiveTestData")
    private Object[][] positiveTestData() {
        return new Object[][]{
                // 你我贷资方 - 借款绑卡
                {DEFAULT_VERIFY_CODE, PARTNER_NIWODAI, DEFAULT_CHANNEL_CODE, LOAN_BIND_TYPE},
                // 你我贷资方 - 还款绑卡
                {DEFAULT_VERIFY_CODE, PARTNER_NIWODAI, DEFAULT_CHANNEL_CODE, REPAYMENT_BIND_TYPE},
                // 火山融 - 借款绑卡
                {ALTERNATIVE_VERIFY_CODE, PARTNER_HUOSHANRONG, DEFAULT_CHANNEL_CODE, LOAN_BIND_TYPE},
                // 火山融 - 还款绑卡
                {ALTERNATIVE_VERIFY_CODE, PARTNER_HUOSHANRONG, DEFAULT_CHANNEL_CODE, REPAYMENT_BIND_TYPE},
                // 哈罗 - 借款绑卡
                {ALTERNATIVE_VERIFY_CODE, PARTNER_HALUO, DEFAULT_CHANNEL_CODE, LOAN_BIND_TYPE},
                // 哈罗 - 还款绑卡
                {ALTERNATIVE_VERIFY_CODE, PARTNER_HALUO, DEFAULT_CHANNEL_CODE, REPAYMENT_BIND_TYPE},
        };
    }

    /**
     * 反向测试数据提供者
     * 包含不支持的卡bin测试数据
     */
    @DataProvider(name = "negativeTestData")
    private Object[][] negativeTestData() {
        return new Object[][]{
                // 你我贷资方 - 借款绑卡（不支持卡bin）
                {PARTNER_NIWODAI, DEFAULT_CHANNEL_CODE, LOAN_BIND_TYPE},
                // 你我贷资方 - 还款绑卡（不支持卡bin）
                {PARTNER_NIWODAI, DEFAULT_CHANNEL_CODE, REPAYMENT_BIND_TYPE},
                // 火山融 - 借款绑卡（不支持卡bin）
                {PARTNER_HUOSHANRONG, DEFAULT_CHANNEL_CODE, LOAN_BIND_TYPE},
                // 火山融 - 还款绑卡（不支持卡bin）
                {PARTNER_HUOSHANRONG, DEFAULT_CHANNEL_CODE, REPAYMENT_BIND_TYPE},
                // 哈罗 - 借款绑卡（不支持卡bin）
                {PARTNER_HALUO, DEFAULT_CHANNEL_CODE, LOAN_BIND_TYPE},
                // 哈罗 - 还款绑卡（不支持卡bin）
                {PARTNER_HALUO, DEFAULT_CHANNEL_CODE, REPAYMENT_BIND_TYPE},
        };
    }


}
