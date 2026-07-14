package Application.TestCase.LoanMarket.TestCase.Zy;

import Application.DataFactory.LoanMarket.H5ProcessContext;
import Application.DataFactory.LoanMarket.XYJH5Process;
import Data.ApisLayer.LoanMarket.OrderController;
import Data.Enum.LoanMarket.LoanUsageEnum;
import Data.Ro.LoanMarket.OrderControllerCreateRo;
import Service.Core.BaseTestCase;
import Service.Util.Universal.IntegerHandler;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 提现测试
 */
public class Loan_WithdrawTest extends BaseTestCase {

    static OrderController orderController = new OrderController();

    // 产品代码配置，方便维护和扩展
    private static final String[] PRODUCT_CODES = {"NIWODAI"};

    @Test(testName = "遍历借款用途所有枚举值——提现成功", dataProvider = "DP_Withdraw_AllUsage", enabled = false)
    public static void TC_Withdraw_01(String productCode, String usageValue, OrderControllerCreateRo ro) {
        ro.setProductCode(productCode);
        ro.setUsage(usageValue);
        orderController.create(ro);
    }


    @DataProvider(name = "DP_Withdraw_AllUsage")
    private Object[][] DP_Withdraw_AllUsage() {
        List<Object[]> testData = new ArrayList<>();
        // 遍历所有产品代码
        for (String productCode : PRODUCT_CODES) {
            // 遍历所有借款用途枚举值
            for (LoanUsageEnum usageEnum : LoanUsageEnum.values()) {
                testData.add(new Object[]{
                        productCode,
                        usageEnum.getValue(),
                        new OrderControllerCreateRo(productCode, usageEnum.getValue())
                });
            }
        }
        return testData.toArray(new Object[0][]);
    }

    // ==================== 借款金额(loanAmt)测试用例 ====================

    /**
     * 借款金额统一测试方法
     * 测试设计方法：正向/反向用例、等价类划分、边界值分析、场景法、正交实验法
     */
    @Test(testName = "借款金额测试", dataProvider = "DP_LoanAmt")
    public void TC_Withdraw_LoanAmt(String testDesc, String productCode, String amountType, Integer fixedAmount) {
        System.out.println("case: " + testDesc);
        H5ProcessContext context = buildContext(productCode);
        int loanAmt = calculateLoanAmount(context, amountType, fixedAmount);
        OrderControllerCreateRo ro = buildRoWithLoanAmt(context, loanAmt);
        orderController.create(ro);
    }

    // ==================== DataProvider ====================

    @DataProvider(name = "DP_LoanAmt")
    private Object[][] DP_LoanAmt() {
        List<Object[]> testData = new ArrayList<>();

        for (String productCode : PRODUCT_CODES) {
            // ===== 正向用例 - 等价类划分：有效等价类 =====
            testData.add(new Object[]{"最小有效金额-提现成功", productCode, "MIN", null});
            testData.add(new Object[]{"最大有效金额-提现成功", productCode, "MAX", null});
            testData.add(new Object[]{"中间有效金额(100的倍数)-提现成功", productCode, "MIDDLE", null});

            // ===== 边界值分析 - 正向 =====
            testData.add(new Object[]{"最小金额边界值-提现成功", productCode, "MIN", null});
            testData.add(new Object[]{"最大金额边界值-提现成功", productCode, "MAX", null});
            testData.add(new Object[]{"最小金额+100-提现成功", productCode, "MIN_PLUS_100", null});
            testData.add(new Object[]{"最大金额-100-提现成功", productCode, "MAX_MINUS_100", null});

            // ===== 反向用例 - 等价类划分：无效等价类 =====
            testData.add(new Object[]{"小于最小金额-提现失败", productCode, "MIN_MINUS_100", null});
            testData.add(new Object[]{"大于最大金额-提现失败", productCode, "MAX_PLUS_100", null});
            testData.add(new Object[]{"不是100的倍数-提现失败", productCode, "NON_MULTIPLE_100", null});
            testData.add(new Object[]{"负数-提现失败", productCode, "NEGATIVE", null});
            testData.add(new Object[]{"零-提现失败", productCode, "ZERO", 0});

            // ===== 边界值分析 - 反向 =====
            testData.add(new Object[]{"最小金额边界-1-提现失败", productCode, "MIN_MINUS_1", null});
            testData.add(new Object[]{"最大金额边界+1-提现失败", productCode, "MAX_PLUS_1", null});

            // ===== 场景法 =====
            testData.add(new Object[]{"场景测试-随机有效金额-提现成功", productCode, "RANDOM", null});
        }

        return testData.toArray(new Object[0][]);
    }

    // ==================== 辅助方法 ====================

    /**
     * 构建H5流程上下文
     */
    private H5ProcessContext buildContext(String productCode) {
        return XYJH5Process.H5processByPartner(productCode, XYJH5Process.H5ProcessStage.BIND_CARD);
    }

    /**
     * 构建指定借款金额的OrderControllerCreateRo对象
     */
    private OrderControllerCreateRo buildRoWithLoanAmt(H5ProcessContext context, int loanAmt) {
        System.out.println(context);
        return new OrderControllerCreateRo(
                context.getProductCode(),
                LoanUsageEnum.getRandom(),
                parseInt(context.getCardId()),
                context.getPeriod(),
                loanAmt
        );
    }

    /**
     * 根据金额类型计算借款金额
     * 支持所有测试场景的金额计算
     */
    private int calculateLoanAmount(H5ProcessContext context, String amountType, Integer fixedAmount) {
        // 如果有固定金额，直接返回
        if (fixedAmount != null) {
            return fixedAmount;
        }

        int minAmt = parseInt(context.getMinBorrowingAmt());
        int maxAmt = parseInt(context.getAvailableAmount());

        switch (amountType) {
            case "MIN":
                return minAmt;
            case "MAX":
                return maxAmt;
            case "MIDDLE":
                return (minAmt + maxAmt) / 2 / 100 * 100;  // 向下取整到100的倍数
            case "MIN_PLUS_100":
                return minAmt + 100;
            case "MAX_MINUS_100":
                return maxAmt - 100;
            case "MIN_MINUS_100":
                return minAmt - 100;
            case "MAX_PLUS_100":
                return maxAmt + 100;
            case "MIN_MINUS_1":
                return minAmt - 1;
            case "MAX_PLUS_1":
                return maxAmt + 1;
            case "NON_MULTIPLE_100":
                return minAmt + IntegerHandler.generateNonMultipleRandom(100, 4);
            case "NEGATIVE":
                return -minAmt;
            case "ZERO":
                return 0;
            case "RANDOM":
                int range = (maxAmt - minAmt) / 100;
                return range <= 0 ? minAmt : minAmt + ((int) (Math.random() * (range + 1)) * 100);
            default:
                return minAmt;
        }
    }

    /**
     * 字符串转整数
     */
    private static int parseInt(String number) {
        return Integer.parseInt(number);
    }
}
