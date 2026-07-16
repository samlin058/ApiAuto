package Data.BusinessLayer.LoanMarket;

import Data.ApisLayer.LoanMarket.BackDoorContoller;
import Data.ApisLayer.LoanMarket.Open;
import Data.Ro.LoanMarket.*;
import Service.Core.ProjectEnum;
import Service.Util.Universal.*;
import Service.Util.variable.BankCardHandler;
import Service.Util.variable.ChinneNameHandler;
import Service.Util.variable.IDNoGeneratorHandler;
import Service.Util.variable.PhoneNumberGeneratorHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 采量方对接标准接口场景流程
 */
public class OpenApiScenario {

    public static void main(String[] args) {
//        processByPartner("luandaoTest3", ProcessStage.LOAN_APPLY, "NIWODAI");

        // openApiProcess("FENQILE", ProcessStage.LOAN_APPLY,"{\"phone\":\"15867339145\",\"idNo\":\"120101199610220570\",\"name\":\"杨文\",\"bankCard\":\"6217002400000000011\"}");
         openApiProcess("FENQILE", ProcessStage.CREDIT_APPLY);
        /*ConcurrentHandler.runConcurrent(
                ()->openApiProcess("FENQILE", ProcessStage.LOAN_APPLY,""),
                5);*/
        //openApiOneStage("FENQILE",ProcessStage.BIND_CARD,"");
    }

    // 配置常量
    private static final int CREDIT_POLLING_TIMES = 100;        // 授信轮询次数
    private static final int CREDIT_INTERVAL_TIMES = 5000;     // 授信轮询间隔时间（单位：ms）
    private static final int LOAN_POLLING_TIMES = 100;          // 借款查询轮询次数
    private static final int LOAN_INTERVAL_TIMES = 5000;       // 借款查询轮询间隔时间（单位：ms）
    private static final int REPAY_POLLING_TIMES = 12;         // 还款查询轮询次数
    private static final int REPAY_INTERVAL_TIMES = 5000;      // 还款查询轮询间隔时间（单位：ms）
    private static final int MAX_BIND_CARD_TIMES = 10;         // 换卡绑卡最大尝试次数

    // 业务常量
    private static final String SUCCESS_CODE = "000000";  //接口返回成功
    private static final String SUCCESS_STATUS = "success";
    private static final String MOCK_PRODUCT_CODE = "RUIDAI";

    // 状态常量
    private static final String LOAN_STATUS_VERIFY = "VERIFY"; // 短信校验
    private static final String LOAN_STATUS_PAYING = "LOAN_PAYING"; //放款中
    private static final String LOAN_STATUS_SUCCESS = "LOAN_SUCCESS";//放款成功
    private static final String LOAN_STATUS_FAIL = "LOAN_FAIL";//放款失败
    private static final String REPAY_STATUS_PROCESS = "PROCESS"; // 还款中
    private static final String REPAY_STATUS_SUCCESS = "SUCCESS";//还款成功
    private static final String REPAY_STATUS_FAIL = "FAIL";//还款失败
    private static final String REPAY_STATUS_PART_SUCCESS = "PART_SUCCESS"; //部分还款

    /**
     * 流程执行阶段枚举
     */
    public enum ProcessStage {
        USER_CHECK("用户准入检查"),
        CREDIT_APPLY("授信申请"),
        BIND_CARD("绑卡流程"),
        LOAN_APPLY("借款申请"),
        LOAN_REPAY("还款流程"),
        GEI_H5URL("获取H5url");

        private final String description;

        ProcessStage(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * 执行贷款流程到指定阶段
     *
     * @param endStage 流程结束阶段，执行到该阶段后停止
     */
    public static LoanProcessContext openApiProcess(String channel, ProcessStage endStage) {
        return openApiProcess(channel, endStage, null);
    }


    /**
     * 指定下游资方走借款流程
     *
     * @param channelCode 渠道
     * @param endStage    流程结束阶段，执行到该阶段后停止
     * @param partnerCode 资方code
     */
    public static LoanProcessContext processByPartner(String channelCode, ProcessStage endStage, String partnerCode) {
        // 给渠道设置指定下游资方
        ChannelRoutingConfiguration.changeCapital(channelCode, partnerCode);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return openApiProcess(channelCode, endStage);
    }

    /**
     * 指定走某一个步骤
     *
     * @param context  上下文
     * @param endStage 步骤
     */
    public static void openApiOneStage(LoanProcessContext context, ProcessStage endStage) {
        try {
            switch (endStage) {
                case USER_CHECK:
                    boolean userCheckResult = performUserCheck(context);
                    System.out.println("用户准入检查结果: " + userCheckResult);
                    break;
                case CREDIT_APPLY:
                    String creditResult = performCreditApply(context);
                    System.out.println("授信申请结果: " + creditResult);
                    break;
                case BIND_CARD:
                    String bankCardResult = performBindCardProcessOne(context);
                    System.out.println("绑卡结果: " + bankCardResult);
                    break;
                case LOAN_APPLY:
                    String creditRes = performCreditApply(context);
                    if (creditRes == null) {
                        System.out.println("授信结果未出，无法进行借款申请");
                        break;
                    }
                    Integer creditOrderStatus = (Integer) JsonHandler.dealOpenApiResponseData(creditRes, "$.creditOrderStatus");
                    if (creditOrderStatus == 2) {
                        System.out.println("授信失败，无法进行借款申请");
                        break;
                    }
                    String bankCard = performBindCardProcessOne(context);
                    if (bankCard == null) {
                        System.out.println("绑卡失败，无法进行借款申请");
                        break;
                    }
                    boolean loanResult = performLoanApply(context, creditRes, bankCard);
                    System.out.println("借款申请结果: " + loanResult);
                    break;
                case LOAN_REPAY:
                    // 需要先做授信、绑卡、借款
                    String creditResRepay = performCreditApply(context);
                    if (creditResRepay == null) {
                        System.out.println("授信结果未出，无法进行还款");
                        break;
                    }
                    Integer creditOrderStatusRepay = (Integer) JsonHandler.dealOpenApiResponseData(creditResRepay, "$.creditOrderStatus");
                    if (creditOrderStatusRepay == 2) {
                        System.out.println("授信失败，无法进行还款");
                        break;
                    }
                    String bankCardRepay = performBindCardProcessOne(context);
                    if (bankCardRepay == null) {
                        System.out.println("绑卡失败，无法进行还款");
                        break;
                    }
                    boolean loanResultRepay = performLoanApply(context, creditResRepay, bankCardRepay);
                    if (!loanResultRepay) {
                        System.out.println("借款失败，无法进行还款");
                        break;
                    }
                    performLoanRepay(context);
                    System.out.println("还款流程已执行");
                    break;
                default:
                    System.out.println("未知的流程阶段: " + endStage);
            }
        } catch (Exception e) {
            System.err.println("openApiOneStage执行异常: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * 执行贷款流程到指定阶段
     *
     * @param endStage 流程结束阶段，执行到该阶段后停止
     */
    public static LoanProcessContext openApiProcess(String channel, ProcessStage endStage, String json) {
        LoanProcessContext context = null;
        try {
            // 初始化基础数据
            if (StringHandler.isEmpty(json)) {
                context = initializeProcessContext(channel);
            } else {
                context = initializeProcessContext(channel, json);
            }
            System.out.println("开始贷款流程，用户手机号：" + context.phone);
            // 1. 准入检查
            if (!performUserCheck(context)) {
                System.out.println("撞库失败");
                return context;
            }
            if (endStage == ProcessStage.USER_CHECK) {
                return context;
            }

            // 2. 授信申请
            String creditResult = performCreditApply(context);
            if (creditResult == null) {
                System.out.println("授信结果未出");
                return context;
            }
            if (endStage == ProcessStage.CREDIT_APPLY) {
                return context;
            }

            //授信失败不往下走
            Integer creditOrderStatus = (Integer) JsonHandler.dealOpenApiResponseData(creditResult, "$.creditOrderStatus");
            if (creditOrderStatus == 2) {
                System.out.println("授信失败，借款流程中止");
                return context;
            }

            // 获取H5链接进行提现
            getH5Link(context);
            if (endStage == ProcessStage.GEI_H5URL) {
                return context;
            }

            // 3. 绑卡流程
            String finalBankCard = performBindCardProcessOne(context);
            if (finalBankCard == null) {
                System.out.println("绑卡失败");
                return context;
            }
            if (endStage == ProcessStage.BIND_CARD) {
                return context;
            }


            // 4. 借款流程
            Boolean LoanResult = performLoanApply(context, creditResult, finalBankCard);
            if (endStage == ProcessStage.LOAN_APPLY) {
                return context;
            }

            // 5. 还款流程
            if (LoanResult) {
                performLoanRepay(context);
            }

            System.out.println("贷款流程完成，用户手机号：" + context.phone);

        } catch (Exception e) {
            System.err.println("贷款流程执行异常: " + e.getMessage());
            e.printStackTrace();
        }
        return  context;
    }

    /**
     * 执行贷款流程到指定阶段（使用已有的上下文）
     *
     * @param context 贷款流程上下文
     * @param endStage 流程结束阶段，执行到该阶段后停止
     */
    public static LoanProcessContext openApiProcess(LoanProcessContext context, ProcessStage endStage) {
        try {
            System.out.println("开始贷款流程，用户手机号：" + context.phone);
            // 1. 准入检查
            if (!performUserCheck(context)) {
                System.out.println("撞库失败");
                return context;
            }
            if (endStage == ProcessStage.USER_CHECK) {
                return context;
            }

            // 2. 授信申请
            String creditResult = performCreditApply(context);
            if (creditResult == null) {
                System.out.println("授信结果未出");
                return context;
            }
            if (endStage == ProcessStage.CREDIT_APPLY) {
                return context;
            }

            //授信失败不往下走
            Integer creditOrderStatus = (Integer) JsonHandler.dealOpenApiResponseData(creditResult, "$.creditOrderStatus");
            if (creditOrderStatus == 2) {
                System.out.println("授信失败，借款流程中止");
                return context;
            }

            // 获取H5链接进行提现
            getH5Link(context);
            if (endStage == ProcessStage.GEI_H5URL) {
                return context;
            }

            // 3. 绑卡流程
            String finalBankCard = performBindCardProcessOne(context);
            if (finalBankCard == null) {
                System.out.println("绑卡失败");
                return context;
            }
            if (endStage == ProcessStage.BIND_CARD) {
                return context;
            }


            // 4. 借款流程
            Boolean LoanResult = performLoanApply(context, creditResult, finalBankCard);
            if (endStage == ProcessStage.LOAN_APPLY) {
                return context;
            }

            // 5. 还款流程
            if (LoanResult) {
                performLoanRepay(context);
            }

            System.out.println("贷款流程完成，用户手机号：" + context.phone);

        } catch (Exception e) {
            System.err.println("贷款流程执行异常: " + e.getMessage());
            e.printStackTrace();
        }
        return context;
    }

    /**
     * 执行贷款流程到指定阶段(绑卡重试机制)
     *
     * @param endStage 流程结束阶段，执行到该阶段后停止
     */
    public static void openApiProcessRetryBindCard(String channel, ProcessStage endStage, String json) {
        try {
            // 初始化基础数据
            LoanProcessContext context = initializeProcessContext(channel, json);
            System.out.println("开始贷款流程，用户手机号：" + context.phone);
            // 1. 准入检查
            if (!performUserCheck(context)) {
                System.out.println("撞库失败");
                return;
            }
            if (endStage == ProcessStage.USER_CHECK) {
                return;
            }

            // 2. 授信申请
            String creditResult = performCreditApply(context);
            if (creditResult == null) {
                System.out.println("授信结果未出");
                return;
            }
            if (endStage == ProcessStage.CREDIT_APPLY) {
                return;
            }

            //授信失败不往下走
            Integer creditOrderStatus = (Integer) JsonHandler.dealOpenApiResponseData(creditResult, "$.creditOrderStatus");
            if (creditOrderStatus == 2) {
                System.out.println("授信失败，借款流程中止");
                return;
            }

            // 3. 绑卡流程
            String finalBankCard = performBindCardProcessOne(context);
            if (finalBankCard == null) {
                System.out.println("绑卡失败");
                return;
            }
            if (endStage == ProcessStage.BIND_CARD) {
                return;
            }

            // 4. 借款流程
            Boolean LoanResult = performLoanApply(context, creditResult, finalBankCard);
            if (endStage == ProcessStage.LOAN_APPLY) {
                return;
            }

            // 5. 还款流程
            if (LoanResult) {
                performLoanRepay(context);
            }

            System.out.println("贷款流程完成，用户手机号：" + context.phone);

        } catch (Exception e) {
            System.err.println("贷款流程执行异常: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 贷款流程上下文数据
     */
    @Data
    public static class LoanProcessContext {
        final String phone, idNo, name, bankCard, channelCode, creditOrderNo, loanOrderNo, repayOrderNo;

        LoanProcessContext(String channelCode) {
            // this(channelCode, null);
            this.channelCode = channelCode;
            this.phone = String.valueOf(PhoneNumberGeneratorHandler.findAvailablePhoneByProjectCode(ProjectEnum.LoanMarket));
            this.idNo = IDNoGeneratorHandler.generateIDCard();
            this.name = ChinneNameHandler.generateName();
            this.bankCard = BankCardHandler.generate19DigitCardNumber();
            this.creditOrderNo = generateCreditOrderNo();
            this.loanOrderNo = generateLoanOrderNo();
            this.repayOrderNo = generateRepayOrderNo();
        }

        LoanProcessContext(String channelCode, String jsonStr) {
            this.channelCode = channelCode;
            Map<String, Object> paramMap = new HashMap<>();
            if (jsonStr != null && !jsonStr.trim().isEmpty()) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    paramMap = mapper.readValue(jsonStr, Map.class);
                } catch (Exception e) {
                    throw new RuntimeException("JSON解析失败: " + e.getMessage(), e);
                }
            }
            this.phone = String.valueOf(paramMap.getOrDefault("phone", PhoneNumberGeneratorHandler.findAvailablePhoneByProjectCode(ProjectEnum.LoanMarket)));
            this.idNo = String.valueOf(paramMap.getOrDefault("idNo", IDNoGeneratorHandler.generateIDCard()));
            this.name = String.valueOf(paramMap.getOrDefault("name", ChinneNameHandler.generateName()));
            this.bankCard = String.valueOf(paramMap.getOrDefault("bankCard", BankCardHandler.generate19DigitCardNumber()));
            this.creditOrderNo = String.valueOf(paramMap.getOrDefault("creditOrderNo", generateCreditOrderNo()));
            this.loanOrderNo = String.valueOf(paramMap.getOrDefault("loanOrderNo", generateLoanOrderNo()));
            this.repayOrderNo = String.valueOf(paramMap.getOrDefault("repayOrderNo", generateRepayOrderNo()));
        }

        private String generateCreditOrderNo() {
            return channelCode + "_" + System.currentTimeMillis() + "_" +
                    UUID.randomUUID().toString().replace("-", "");
        }

        private String generateLoanOrderNo() {
            return channelCode + "_Loan_" + TimestampHandler.getCurrentTimestamp() +
                    UUID.randomUUID().toString().replace("-", "");
        }

        private String generateRepayOrderNo() {
            return channelCode + "_repay_" + TimestampHandler.getCurrentTimestamp();
        }
    }

    /**
     * 初始化流程上下文
     */
    private static LoanProcessContext initializeProcessContext(String channel) {
        return new LoanProcessContext(channel);
    }

    private static LoanProcessContext initializeProcessContext(String channel, String json) {
        return new LoanProcessContext(channel, json);
    }

    /**
     * 执行用户准入检查
     */
    private static boolean performUserCheck(LoanProcessContext context) {
        try {
            Open openController = new Open();
            String response = openController.userCheckService(
                    new OpenUserCheckServiceRo(context.channelCode, context.phone, context.idNo, context.name)
            );
            System.out.println("准入结果：" + response);
            Boolean checkResult = (Boolean) JsonHandler.dealOpenApiResponseData(response, "$.checkResult");
            return checkResult != null && checkResult;
        } catch (Exception e) {
            System.err.println("用户准入检查异常: " + e.getMessage());
            return false;
        }
    }

    /**
     * 执行授信申请
     */
    private static String performCreditApply(LoanProcessContext context) {
        try {
            Open openController = new Open();
            // 授信申请
            String creditApplyResult = openController.creditApplyService(
                    new OpenCreditApplyServiceRo(context.channelCode, context.creditOrderNo, context.phone, context.idNo, context.name)
            );
            System.out.println("授信申请结果：" + creditApplyResult);
            String creditApplyResultCode = JsonHandler.readStringByJsonPath("$.code", creditApplyResult);
            if (!SUCCESS_CODE.equalsIgnoreCase(creditApplyResultCode)) {
                System.out.println("授信申请失败：" + creditApplyResult);
                return null;
            }

            // 轮询授信结果
            return pollCreditResult(openController, context);

        } catch (Exception e) {
            System.err.println("授信申请异常: " + e.getMessage());
            return null;
        }
    }

    /**
     * 轮询授信结果
     */
    private static String pollCreditResult(Open openController, LoanProcessContext context) {
        return PollingHandler.pollUntilSuccess(
                () -> openController.creditResultQueryService(
                        new OpenCreditResultQueryServiceRo(context.channelCode, context.creditOrderNo)
                ),
                res -> {
                    try {
                        System.out.println("授信结果查询：" + res);
                        int status = (int) JsonHandler.dealOpenApiResponseData(res, "$.creditOrderStatus");
                        if (status == 0) {
                            System.out.println("授信中...");
                        }
                        if (status == 1) {
                            System.out.println("授信成功：" + res);
                        }
                        if (status == 2) {
                            System.out.println("授信失败：" + res);
                        }
                        return status == 1 || status == 2; // 成功或失败都停止轮询
                    } catch (Exception e) {
                        System.err.println("解析授信状态异常: " + e.getMessage());
                        return false;
                    }
                },
                CREDIT_POLLING_TIMES,
                CREDIT_INTERVAL_TIMES,
                "creditResultQueryService"
        );
    }

    /**
     * 执行绑卡流程
     */
    private static String performBindCardProcessOne(LoanProcessContext context) {
        try {
            // 首次绑卡
            if (BindCard(context.channelCode, context.creditOrderNo, context.bankCard,
                    context.phone, context.idNo, context.name)) {
                return context.bankCard;
            }
            return null;
        } catch (Exception e) {
            System.err.println("绑卡流程异常: " + e.getMessage());
            return null;
        }
    }

    /**
     * 执行绑卡流程（带重试机制）
     */
    private static String performBindCardProcessRetry(LoanProcessContext context) {
        try {
            // 首次绑卡
            if (BindCard(context.channelCode, context.creditOrderNo, context.bankCard,
                    context.phone, context.idNo, context.name)) {
                return context.bankCard;
            }

            // 重试绑卡
            System.out.println("首次绑卡失败，开始重试绑卡");
            for (int i = 0; i < MAX_BIND_CARD_TIMES; i++) {
                String newBankCard = BankCardHandler.generate19DigitCardNumber();
                if (BindCard(context.channelCode, context.creditOrderNo, newBankCard,
                        context.phone, context.idNo, context.name)) {
                    System.out.println("重试绑卡成功，使用新卡号: " + newBankCard);
                    return newBankCard;
                }
            }

            System.out.println("多次换卡绑卡后仍未成功");
            return null;

        } catch (Exception e) {
            System.err.println("绑卡流程异常: " + e.getMessage());
            return null;
        }
    }

    /**
     * 执行借款申请
     */
    private static boolean performLoanApply(LoanProcessContext context, String creditResult, String bankCard) {
        try {
            return loanApply(creditResult, context.channelCode, context.creditOrderNo,
                    context.loanOrderNo, bankCard, "");
        } catch (Exception e) {
            System.err.println("借款申请异常: " + e.getMessage());
            return false;
        }
    }

    /**
     * 执行还款流程
     */
    private static void performLoanRepay(LoanProcessContext context) {
        try {
            loanRepay(context.channelCode, context.loanOrderNo, OpenRepayTypeEnum.NORMAL, "1", "", context.repayOrderNo);
        } catch (Exception e) {
            System.err.println("还款流程异常: " + e.getMessage());
        }
    }


    /**
     * 获取H5短链
     */
    private static void getH5Link(LoanProcessContext context) {
        try {
            Open openController = new Open();
            String url = openController.OpenH5LinkService(new OpenOpenH5LinkServiceRo(context.channelCode, context.creditOrderNo, null));
            System.out.println(url);
        } catch (Exception e) {
            System.err.println("获取H5链接异常: " + e.getMessage());
        }
    }

    /**
     * 信用家绑卡流程
     *
     * @param channelCode   渠道code
     * @param creditOrderNo 授信单号
     * @param bankCardNo    银行卡号
     * @param phoneNumber   手机号
     * @param idNo          身份证号
     * @param name          姓名
     */
    public static Boolean BindCard(String channelCode, String creditOrderNo, String bankCardNo, String phoneNumber, String idNo, String name) {
        try {
            Open openController = new Open();
            // 查询绑定银行卡
            String bindCardResult = openController.bindCardListService(new OpenBindCardListServiceRo(channelCode, creditOrderNo));
            String bindCardResultJson = JsonHandler.readStringByJsonPath("$.data", bindCardResult);
            System.out.println("查询绑定的银行卡:" + bindCardResult);

            if ("{}".equalsIgnoreCase(bindCardResultJson)) {
                // 绑卡申请
                String bindCardApplyResult = openController.bindCardApplyService(
                        new OpenBindCardApplyServiceRo(channelCode, creditOrderNo, bankCardNo, phoneNumber, idNo, name)
                );

                String bindCardApplyResultStatus = (String) JsonHandler.dealOpenApiResponseData(bindCardApplyResult, "$.status");
                System.out.println("绑卡申请结果状态：" + bindCardApplyResultStatus);
                if (SUCCESS_STATUS.equals(bindCardApplyResultStatus)) {
                    // 绑卡确认
                    String bindCardConfirmResult = openController.bindCardConfirmService(
                            new OpenBindCardConfirmServiceRo(channelCode, creditOrderNo, bankCardNo, phoneNumber, idNo, name)
                    );
                    String bindCardConfirmResultStatus = (String) JsonHandler.dealOpenApiResponseData(bindCardConfirmResult, "$.status");
                    if (SUCCESS_STATUS.equals(bindCardConfirmResultStatus)) {
                        System.out.println("绑卡成功:" + bindCardConfirmResult);
                        return true;
                    } else {
                        System.out.println("绑卡确认失败:" + bindCardConfirmResult);
                        return false;
                    }
                } else {
                    System.out.println("绑卡申请失败：" + bindCardApplyResult);
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            System.err.println("绑卡操作异常: " + e.getMessage());
            return false;
        }
    }

    /**
     * 借款申请
     *
     * @param creditResult  授信结果
     * @param channelCode   渠道code
     * @param creditOrderNo 授信订单号
     * @param bankCard      银行卡号
     * @param loanOrderNo   借款申请单号
     */
    public static Boolean loanApply(String creditResult, String channelCode, String creditOrderNo, String loanOrderNo, String bankCard, String smsCode) {
        try {
            Open openController = new Open();
            // 可借款的金额
            Double loanAmount = (Double) JsonHandler.dealOpenApiResponseData(creditResult, "$.availableAmt");
            // 随机获取产品可借期数
            String creditResultJson = JsonHandler.readStringByJsonPath("$.data", creditResult);
            Integer loanPeriod = Integer.valueOf(JsonHandler.getRandomElementFromJsonArrayByPath(creditResultJson, "$.approvalTermList"));
            System.out.println("可借期数:" + loanPeriod + ",可借款的金额" + loanAmount);

            // 借款试算
            String loanTrialResultJson = openController.loanTrialService(new OpenLoanTrialServiceRo(channelCode, creditOrderNo, loanAmount, loanPeriod));
            System.out.println("试算结果：" + loanTrialResultJson);
            String loanTrialResult = JsonHandler.readStringByJsonPath("$.code", loanTrialResultJson);

            if (SUCCESS_CODE.equals(loanTrialResult)) {
                // 借款申请
                String loanApplyResult = openController.loanApplyService(new OpenLoanApplyServiceRo(channelCode, creditOrderNo, loanOrderNo, loanAmount, loanPeriod, bankCard));
                System.out.println("借款申请结果：" + loanApplyResult);
                String loanApplyResultLoanStatus = (String) JsonHandler.dealOpenApiResponseData(loanApplyResult, "$.loanStatus");

                // 需短验
                if (LOAN_STATUS_VERIFY.equalsIgnoreCase(loanApplyResultLoanStatus)) {
                    // 正常来说需要调绑卡申请进行2次确认
                    String loanApplySmsResult = openController.loanApplyService(new OpenLoanApplyServiceRo(channelCode, creditOrderNo, loanOrderNo, loanAmount, loanPeriod, bankCard, smsCode));
                    String loanApplySmsResultJson = (String) JsonHandler.dealOpenApiResponseData(loanApplySmsResult, "");
                    System.out.println("第二次借款申请：" + loanApplySmsResultJson);
                }

                // 借款查询
                return pollLoanResult(openController, channelCode, loanOrderNo);
            } else {
                System.out.println("试算出错：" + loanTrialResultJson);
                return false;
            }
        } catch (Exception e) {
            System.err.println("借款申请异常: " + e.getMessage());
            return false;
        }
    }

    /**
     * 轮询借款结果
     */
    private static boolean pollLoanResult(Open openController, String channelCode, String loanOrderNo) {
        String loanResultQueryResponse = PollingHandler.pollUntilSuccess(
                // 查询操作
                () -> openController.loanResultQueryService(new OpenLoanResultQueryServiceRo(channelCode, loanOrderNo)),
                // 判断是否放款成功
                res -> {
                    try {
                        if (SUCCESS_CODE.equalsIgnoreCase((String) JsonHandler.readValueByJsonPath("$.code", res))) {
                            String loanResult = (String) JsonHandler.dealOpenApiResponseData(res, "$.loanStatus");
                            String thirdLoanOrderNo = (String) JsonHandler.dealOpenApiResponseData(res, "$.thirdLoanOrderNo");

                            if (LOAN_STATUS_PAYING.equalsIgnoreCase(loanResult)) {
                                System.out.println("放款中");
                                handleLoanPaying(thirdLoanOrderNo);
                                return false;
                            }

                            if (LOAN_STATUS_SUCCESS.equalsIgnoreCase(loanResult)) {
                                System.out.println("放款成功");
                                return true;
                            }

                            if (LOAN_STATUS_FAIL.equalsIgnoreCase(loanResult)) {
                                System.out.println("放款失败：" + res);
                                return true;
                            }
                        } else {
                            System.out.println("借款申请后，查询借款结果出错：" + res);
                            return true;
                        }
                        return false;
                    } catch (Exception e) {
                        System.err.println("解析借款状态异常: " + e.getMessage());
                        return false;
                    }
                },
                LOAN_POLLING_TIMES,    // 最大轮询次数
                LOAN_INTERVAL_TIMES,   // 每次间隔5秒
                "loanResultQueryService"
        );
        return !StringHandler.isEmpty(loanResultQueryResponse);
    }

    /**
     * 处理放款中状态
     */
    private static void handleLoanPaying(String thirdLoanOrderNo) {
        try {
            // 获取借款订单id
            String getLoanIdSql = "select id from tbl_order where order_no = ? order by id desc limit 1;";
            String getLoanIdSqlResult = JDBCHandler.queryFirstRowAsJson(ProjectEnum.LoanMarket, getLoanIdSql, thirdLoanOrderNo);
            String loanIdStr = JsonHandler.readStringByJsonPath("$.id", getLoanIdSqlResult);
            Integer loanId = Integer.parseInt(loanIdStr);

            // 获取产品code
            String getProductSql = "select product_code from tbl_order where order_no = ? order by id desc limit 1;";
            String getProductCodeSqlResult = JDBCHandler.queryFirstRowAsJson(ProjectEnum.LoanMarket, getProductSql, thirdLoanOrderNo);
            String productCode = JsonHandler.readStringByJsonPath("$.product_code", getProductCodeSqlResult);

            // 如果是RuiDai，可以mock放款成功
            if (MOCK_PRODUCT_CODE.equalsIgnoreCase(productCode)) {
                BackDoorContoller backDoorContoller = new BackDoorContoller();
                backDoorContoller.updateLoan(new BackDoorContollerUpdateLoanRo(loanId));
                System.out.println("已触发RuiDai产品放款成功");
            }
        } catch (Exception e) {
            System.err.println("处理放款中状态异常: " + e.getMessage());
        }
    }

    /**
     * 还款
     *
     * @param channelCode  渠道
     * @param loanOrderNo  借款订单号
     * @param repayType    还款方式
     * @param periods      期数
     * @param smsCode      短信验证码
     * @param repayOrderNo 还款单号
     */
    public static void loanRepay(String channelCode, String loanOrderNo, OpenRepayTypeEnum repayType, String periods, String smsCode, String repayOrderNo) {
        try {
            Open openController = new Open();
            // 查询还款计划
            openController.repayPlanQueryService(new OpenRepayPlanQueryServiceRo(channelCode, loanOrderNo));
            // 还款试算
            String repayTrialResult = openController.repayTrialService(new OpenRepayTrialServiceRo(channelCode, loanOrderNo, repayType, periods));
            String repayTrialStatus = (String) JsonHandler.dealOpenApiResponseData(repayTrialResult, "$.status");

            if (SUCCESS_STATUS.equalsIgnoreCase(repayTrialStatus)) {
                String repayApplyResult = openController.repayApplyService(new OpenRepayApplyServiceRo(channelCode, loanOrderNo, repayOrderNo, repayType, periods, smsCode));
                System.out.println("还款申请结果：" + repayApplyResult);
                // 还款结果查询
                pollRepayResult(openController, channelCode, repayOrderNo, loanOrderNo);
            } else {
                System.out.println("还款试算失败: " + repayTrialResult);
            }
        } catch (Exception e) {
            System.err.println("还款流程异常: " + e.getMessage());
        }
    }

    /**
     * 还款
     *
     * @param uid     用户id
     * @param periods 还款期数
     */
    public static void loanRepay(String uid, OpenRepayTypeEnum repayType, String periods) {
        String sql = "select channel_code,channel_request_id from tbl_order where uid =? and loan_status ='LOAN_SUCCESS' order by id desc; ";
        String sqlResult = JDBCHandler.queryFirstRowAsJson(ProjectEnum.LoanMarket, sql, uid);
        String channelCode = JsonHandler.readStringByJsonPath("$.channel_code", sqlResult);
        String loanOrderNo = JsonHandler.readStringByJsonPath("$.channel_request_id", sqlResult);
        ;
        String repayOrderNo = TimestampHandler.getCurrentTimestamp() + "11";
        String smsCode = null;
        try {
            Open openController = new Open();
            // 查询还款计划
            openController.repayPlanQueryService(new OpenRepayPlanQueryServiceRo(channelCode, loanOrderNo));
            // 还款试算
            String repayTrialResult = openController.repayTrialService(new OpenRepayTrialServiceRo(channelCode, loanOrderNo, repayType, periods));
            String repayTrialStatus = (String) JsonHandler.dealOpenApiResponseData(repayTrialResult, "$.status");

            if (SUCCESS_STATUS.equalsIgnoreCase(repayTrialStatus)) {
                String repayApplyResult = openController.repayApplyService(new OpenRepayApplyServiceRo(channelCode, loanOrderNo, repayOrderNo, repayType, periods, smsCode));
                System.out.println("还款申请结果：" + repayApplyResult);
                // 还款结果查询
                pollRepayResult(openController, channelCode, repayOrderNo, loanOrderNo);
            } else {
                System.out.println("还款试算失败: " + repayTrialResult);
            }
        } catch (Exception e) {
            System.err.println("还款流程异常: " + e.getMessage());
        }
    }

    public enum OpenRepayTypeEnum {
        NORMAL("normal", "正常"),
        EARLY("early", "提前结清");

        private final String code;
        private final String desc;

        OpenRepayTypeEnum(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 轮询还款结果
     */
    private static void pollRepayResult(Open openController, String channelCode, String repayOrderNo, String loanOrderNo) {
        String repayResultQueryResponse = PollingHandler.pollUntilSuccess(
                // 查询操作
                () -> {
                    System.out.println("还款结果查询");
                    return openController.repayResultQueryService(new OpenRepayResultQueryServiceRo(channelCode, repayOrderNo, loanOrderNo));
                },
                // 判断是否达到终态
                res -> {
                    try {
                        String loanRepayStatus = (String) JsonHandler.dealOpenApiResponseData(res, "$.status");

                        if (REPAY_STATUS_PROCESS.equalsIgnoreCase(loanRepayStatus)) {
                            System.out.println("还款中");
                            return false;
                        }

                        if (REPAY_STATUS_SUCCESS.equalsIgnoreCase(loanRepayStatus)) {
                            System.out.println("还款成功");
                            return true;
                        }

                        if (REPAY_STATUS_FAIL.equalsIgnoreCase(loanRepayStatus)) {
                            System.out.println("还款失败");
                            return true;
                        }

                        if (REPAY_STATUS_PART_SUCCESS.equalsIgnoreCase(loanRepayStatus)) {
                            System.out.println("部分还款成功");
                            return true;
                        }
                        return false;
                    } catch (Exception e) {
                        System.err.println("解析还款状态异常: " + e.getMessage());
                        return false;
                    }
                },
                REPAY_POLLING_TIMES,    // 最大轮询次数
                REPAY_INTERVAL_TIMES,   // 每次间隔5秒
                "repayResultQueryService"
        );
        System.out.println("repayResultQueryResponse：" + repayResultQueryResponse);
    }
}
