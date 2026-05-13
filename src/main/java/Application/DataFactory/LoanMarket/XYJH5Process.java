package Application.DataFactory.LoanMarket;

import Application.DBoperation.LoanMarket.User;
import Application.DBoperation.LoanMarket.routing;
import Data.Apis.LoanMarket.AssessmentController;
import Data.Apis.LoanMarket.CardController;
import Data.Apis.LoanMarket.OrderController;
import Data.Apis.LoanMarket.RepayPlanController;
import Data.Enum.LoanMarket.LoanUsageEnum;
import Data.Ro.LoanMarket.*;
import Service.Util.variable.BankCardHandler;
import Service.Util.Universal.JsonHandler;
import Service.Util.Universal.PollingHandler;
import Service.Util.Universal.StringHandler;

import java.util.List;
import java.util.Random;

import static Application.DataFactory.LoanMarket.H5ProcessContext.*;


/**
 * 信用家H5端流程
 */
public class XYJH5Process {
    // 配置常量
    private static final int CREDIT_POLLING_TIMES = 12;        // 授信轮询次数
    private static final int CREDIT_INTERVAL_TIMES = 5000;     // 授信轮询间隔时间（单位：ms）
    private static final int LOAN_POLLING_TIMES = 12;          // 借款查询轮询次数
    private static final int LOAN_INTERVAL_TIMES = 5000;       // 借款查询轮询间隔时间（单位：ms）
    private static final String DEFAULT_CHANNEL_CODE = "luandaoTest2";       // 借款查询轮询间隔时间（单位：ms）

    public static void main(String[] args) {
       H5processByPartner("RUIDAI", H5ProcessStage.CREDIT_APPLY);
//        H5processByPartner(836372,"ZHIJIE_HALF", H5ProcessStage.CREDIT_APPLY);
//        H5processByPartner(81185, "HEXIAOBAO_HALF", H5ProcessStage.CREDIT_APPLY);
//      H5process("luandaoTest3", H5ProcessStage.CREDIT_APPLY);
//        H5processWithoutFilter("luandaoTest3", H5ProcessStage.CREDIT_APPLY);
         /* ConcurrentHandler.runConcurrent(
                ()->H5process("luandaoTest2", H5ProcessStage.LOAN_APPLY),
                2);*/
    }

    /**
     * H5信用家指定渠道用户走指定资方进件
     *
     * @param channelCode    用户渠道
     * @param partnerCode    资方
     * @param h5ProcessStage H5流程执行阶段
     */
    public static H5ProcessContext H5processByPartner(String channelCode, String partnerCode, H5ProcessStage h5ProcessStage) {
        // 初始化流程上下文（创建用户）
        H5ProcessContext context = getH5ProcessContext(channelCode, partnerCode);
        // 执行H5流程
        return H5process(context, h5ProcessStage);
    }

    /**
     * H5信用家指定渠道用户走指定资方进件
     *
     * @param partnerCode    资方
     * @param h5ProcessStage H5流程执行阶段
     */
    public static H5ProcessContext H5processByPartner(String partnerCode, H5ProcessStage h5ProcessStage) {
        // 初始化流程上下文（创建用户）
        H5ProcessContext context = getH5ProcessContext(DEFAULT_CHANNEL_CODE, partnerCode);
        // 执行H5流程
        return H5process(context, h5ProcessStage);
    }

    /**
     * H5信用家指定用户uid走指定资方进件
     *
     * @param uid            用户id
     * @param partnerCode    资方
     * @param h5ProcessStage H5流程执行阶段
     */
    public static void H5processByPartner(int uid, String partnerCode, H5ProcessStage h5ProcessStage) {
        // 初始化流程上下文（创建用户）
        H5ProcessContext context = getH5ProcessContext(uid, partnerCode);
        // 执行H5流程
        H5process(context, h5ProcessStage);
    }

    /**
     * 指定某渠道指定手机号走指定的资方
     *
     * @param phone          用户手机号
     * @param channelCode    用户渠道
     * @param partnerCode    资方
     * @param h5ProcessStage H5流程执行阶段
     */
    public static H5ProcessContext H5processByPartner(String phone, String channelCode, String partnerCode, H5ProcessStage h5ProcessStage) {
        H5ProcessContext context;
        String uidList = User.getUid(phone);
        if ("[]".equals(uidList) && !StringHandler.isEmpty(channelCode)) {
            // 初始化流程上下文（创建用户）
            context = getH5ProcessContext(phone, channelCode, partnerCode);
        } else {
            String uid = StringHandler.trimSameBytesFromBothEnds(JsonHandler.readStringByJsonPath("$.*.id", uidList), 1);
            System.out.println("uid的值：" + uid);
            context = getH5ProcessContext(Integer.parseInt(uid), partnerCode);
        }
        // 执行H5流程
        return H5process(context, h5ProcessStage);
    }


    /**
     * 指定某个渠道的用户走到指定流程
     *
     * @param channelCode    渠道code
     * @param h5ProcessStage 指定流程
     * @return H5ProcessContext
     */
    public static H5ProcessContext H5process(String channelCode, H5ProcessStage h5ProcessStage) {
        List<String> productList = routing.getRoutingCapital(channelCode);
        H5ProcessContext context = getH5ProcessContextByProductList(channelCode,productList);
        return H5process(context, h5ProcessStage);
    }

    /**
     * 指定某个渠道的用户走到指定流程
     *
     * @param channelCode    渠道code
     * @param h5ProcessStage 指定流程
     * @return H5ProcessContext
     */
    public static H5ProcessContext H5processWithoutFilter(String channelCode, H5ProcessStage h5ProcessStage) {
        H5ProcessContext context = getH5ProcessContext(channelCode);
        return H5process(context, h5ProcessStage);
    }

    /**
     * 执行H5流程到指定阶段
     *
     * @param endStage 流程结束阶段，执行到该阶段后停止
     */
    private static H5ProcessContext H5process(H5ProcessContext context, H5ProcessStage endStage) {
        try {
            // 2. 授信申请
            System.out.println("=== " + H5ProcessStage.CREDIT_APPLY.getDescription() + " ===");
            context.assessmentId = JsonHandler.readIntegerByJsonPath("$.data.id", assessmentController.init(new AssessmentControllerInitRo(context.uid)));
            System.out.println("授信申请ID: " + context.assessmentId);
            // 轮询授信结果
            performCreditPolling(context);
            if (!performCreditResultCheck(context)) {
                return context;
            }
            if (endStage == H5ProcessStage.CREDIT_APPLY) {
                return context;
            }

            // 3. 绑卡流程
            System.out.println("=== " + H5ProcessStage.BIND_CARD.getDescription() + " ===");
            context.bindCardResult = performBindCardProcess(context);
            if ("FAIL".equalsIgnoreCase(JsonHandler.readStringByJsonPath("$.data.result", context.bindCardResult))) {
                System.out.println("绑卡失败，流程中止");
                return context;
            }
            context.setCardId(JsonHandler.readStringByJsonPath("$.data.cardId",context.bindCardResult));
            if (endStage == H5ProcessStage.BIND_CARD) {
                return context;
            }

            // 4. 借款试算
            System.out.println("=== " + H5ProcessStage.LOAN_CALCULATE.getDescription() + " ===");
            context.loanPlanCalculateResult = performLoanCalculate(context);
            if (endStage == H5ProcessStage.LOAN_CALCULATE) {
                return context;
            }

            // 5. 借款申请
            System.out.println("=== " + H5ProcessStage.LOAN_APPLY.getDescription() + " ===");
            context.loanApplyResult = performLoanApply(context);
            if ("FAIL".equalsIgnoreCase(JsonHandler.readStringByJsonPath("$.data.status", context.loanApplyResult))) {
                System.out.println("借款申请失败，流程中止");
                return context;
            }
            performLoanPolling(context);
        } catch (Exception e) {
            System.err.println("H5流程执行异常: " + e.getMessage());
            e.printStackTrace();
        }
        return context;
    }

    /**
     * 执行授信轮询
     */
    private static void performCreditPolling(H5ProcessContext context) {
        PollingHandler.pollUntilSuccess(() -> assessmentController.status(new AssessmentControllerStatusRo(context.assessmentId)), res -> {
            try {
                String status = JsonHandler.readStringByJsonPath("$.data.status", res);
                if ("RUNNING".equalsIgnoreCase(status)) {
                    System.out.println("授信中...");
                }
                if ("SUCCEED".equalsIgnoreCase(status)) {
                    String creditResult = assessmentController.result(new AssessmentControllerResultRo(context.assessmentId));
                    Long availableAmt = JsonHandler.readLongByJsonPath("$.data.amt", creditResult);
                    context.setAvailableAmount(availableAmt / 100 + "");
                    String supportPeriods = JsonHandler.readStringByJsonPath("$.data.supportPeriods", creditResult);
                    context.setPeriod(supportPeriods);
                    String minBorrowingAmt = JsonHandler.readStringByJsonPath("$.data.minBorrowingAmt", creditResult);
                    context.setMinBorrowingAmt(minBorrowingAmt);
                    System.out.println("授信成功：" + res);
                }
                if ("FAILED".equalsIgnoreCase(status)) {
                    System.out.println("授信失败：" + res);
                }
                return status.equalsIgnoreCase("SUCCEED") || status.equalsIgnoreCase("FAILED"); // 成功或失败都停止轮询
            } catch (Exception e) {
                System.err.println("解析授信状态异常: " + e.getMessage());
                return false;
            }
        }, CREDIT_POLLING_TIMES, CREDIT_INTERVAL_TIMES, "queryAssessmentStatus");
    }

    /**
     * 执行授信结果检查
     */
    private static boolean performCreditResultCheck(H5ProcessContext context) {
        context.creditResult = assessmentController.result(new AssessmentControllerResultRo(context.assessmentId));
        System.out.println("授信结果:" + context.creditResult);
        Integer code = JsonHandler.readIntegerByJsonPath("$.code", context.creditResult);
        if (code == 1) {
            System.out.println("规定时间内，授信结果未出，请手动查询原因");
            return false;
        }
        String creditResultStatus = JsonHandler.readStringByJsonPath("$.data.status", context.creditResult);
        if ("REJECT".equalsIgnoreCase(creditResultStatus)) {
            System.out.println("授信失败，借款流程中止");
            return false;
        }
        context.productCode = JsonHandler.readStringByJsonPath("$.data.productCode", context.creditResult);
        return true;
    }

    /**
     * 执行绑卡流程
     */
    private static String performBindCardProcess(H5ProcessContext context) {
        System.out.println("支持列表：" + cardController.supportList(new CardControllerSupportListRo(context.productCode, context.uid)));
        String bindCardResult = BindCard(context.mobile, context.productCode, BankCardHandler.generate19DigitCardNumber());
        String bindStatus = JsonHandler.readStringByJsonPath("$.data.result", bindCardResult);
        System.out.println("绑卡结果：" + bindStatus);
        return bindCardResult;
    }

    /**
     * 执行借款试算
     */
    private static String performLoanCalculate(H5ProcessContext context) {
        Integer periods = Integer.valueOf(JsonHandler.getRandomElementFromJsonArrayByPath(context.creditResult, "$.data.supportPeriodList"));
        Integer loanAmt = JsonHandler.readIntegerByJsonPath("$.data.amt", context.creditResult);
        String loanPlanCalculateResult = repayPlanController.planCalculate(new RepayPlanControllerPlanCalculateRo(context.productCode, periods, loanAmt));
        System.out.println("试算结果：" + loanPlanCalculateResult);
        return loanPlanCalculateResult;
    }

    /**
     * 执行借款申请
     */
    private static String performLoanApply(H5ProcessContext context) {
        String loanPeriods = JsonHandler.readStringByJsonPath("$.data.supportPeriods", context.creditResult);
        if (loanPeriods.contains(",")) {
            // 如果包含逗号，说明数据是"12,9,6,3"格式，根据逗号分割后随机选一个重新赋值
            String[] periodsArray = loanPeriods.split(",");
            Random random = new Random();
            int randomIndex = random.nextInt(periodsArray.length);
            loanPeriods = periodsArray[randomIndex];
        }
        Integer loanAmont = JsonHandler.readIntegerByJsonPath("$.data.amt", context.creditResult);
        Integer cardId = JsonHandler.readIntegerByJsonPath("$.data.cardId", context.bindCardResult);
        String createResult = orderController.create(new OrderControllerCreateRo(context.productCode, LoanUsageEnum.getRandom(), cardId, loanPeriods, loanAmont, true));
        System.out.println("借款申请结果：" + createResult);
        context.orderId = JsonHandler.readValueByJsonPath("$.data.orderId", createResult) + "";
        return createResult;
    }

    /**
     * 执行借款轮询
     */
    private static void performLoanPolling(H5ProcessContext context) {
        PollingHandler.pollUntilSuccess(() -> orderController.detail(new OrderControllerDetailRo(context.orderId)), res -> {
            try {
                String status = JsonHandler.readStringByJsonPath("$.data.loanStatus", res);
                if ("LOAN_PAYING".equalsIgnoreCase(status)) {
                    System.out.println("放款中...");
                }
                if ("LOAN_SUCCESS".equalsIgnoreCase(status)) {
                    System.out.println("放款成功：" + res);
                }
                if ("LOAN_FAIL".equalsIgnoreCase(status)) {
                    System.out.println("放款失败：" + res);
                }
                return status.equalsIgnoreCase("LOAN_SUCCESS") || status.equalsIgnoreCase("LOAN_FAIL"); // 成功或失败都停止轮询
            } catch (Exception e) {
                System.err.println("解析授信状态异常: " + e.getMessage());
                return false;
            }
        }, LOAN_POLLING_TIMES, LOAN_INTERVAL_TIMES, "detail");
    }

    /**
     * 绑卡流程
     *
     * @param mobile      手机号
     * @param productCode
     * @param cardNum     银行卡号
     * @return
     */
    private static String BindCard(String mobile, String productCode, String cardNum) {
        String initResult = cardController.initBindCardTask(new CardControllerInitBindCardTaskRo(mobile, productCode, cardNum));
        String tastId = JsonHandler.readStringByJsonPath("$.data.taskId", initResult);
        String verifyCode = "";
        if ("NIWODAI".equalsIgnoreCase(productCode)) {
            verifyCode = "111111";
        }
        String bindCardResult = cardController.bindCardApply(new CardControllerBindCardApplyRo(mobile, productCode, cardNum, tastId));
        System.out.println("首次绑卡申请结果：" + bindCardResult);
        String bindCardResultSatus = JsonHandler.readStringByJsonPath("$.data.result", bindCardResult);
        if ("VERIFY".equalsIgnoreCase(bindCardResultSatus)) {
            String verifyCardResult = cardController.verifyCard(new CardControllerVerifyCardRo(tastId, verifyCode));
            System.out.println("首次绑卡验证码确认结果：" + verifyCardResult);
            String verifyCardResultSecond = JsonHandler.readStringByJsonPath("$.data.result", verifyCardResult);
            if ("FAIL".equalsIgnoreCase(verifyCardResultSecond)) {
                return verifyCardResult;
            }
            if ("BIND_SUCCEED".equalsIgnoreCase(verifyCardResultSecond)) {
                return verifyCardResult;
            }
            String cardReconfirmResult = "";
            if ("NEED_CONFIRM".equalsIgnoreCase(verifyCardResultSecond)) {
                String bindCard2Result = cardController.bindCardApply(new CardControllerBindCardApplyRo(mobile, productCode, cardNum, tastId));
                String bindCardResult2Satus = JsonHandler.readStringByJsonPath("$.data.result", bindCard2Result);
                if ("FAIL".equalsIgnoreCase(bindCardResult2Satus)) {
                    System.out.println("二次申请绑卡结果：" + bindCardResult2Satus);
                    return bindCard2Result;
                }
                if ("VERIFY".equalsIgnoreCase(bindCardResult2Satus)) {
                    cardReconfirmResult = cardController.verifyCard(new CardControllerVerifyCardRo(tastId, verifyCode));
                }
                System.out.println("二次确认结果：" + cardReconfirmResult);
            }
            return cardReconfirmResult;
        }
        if ("BIND_SUCCEED".equalsIgnoreCase(bindCardResultSatus)) {
            return bindCardResult;
        }
        return bindCardResult;
    }

    // 公共Controller对象
    public static final AssessmentController assessmentController = new AssessmentController();
    public static final RepayPlanController repayPlanController = new RepayPlanController();
    public static final OrderController orderController = new OrderController();
    public static final CardController cardController = new CardController();


    /**
     * H5流程执行阶段枚举
     */
    public enum H5ProcessStage {
        CREDIT_APPLY("授信申请"), BIND_CARD("绑卡"), LOAN_CALCULATE("借款试算"), LOAN_APPLY("借款申请");

        private final String description;

        H5ProcessStage(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
