package Service.Pojo;

import Service.Util.Universal.DateHandler;
import Service.Util.variable.GenerationHandler;

import java.util.Date;

@lombok.Data
public class LoanInfo {

    private String productType = "20-18";               // 产品类型
    private Integer loanTerm;                           // 贷款期限
    private Integer loanAmount;                         // 贷款总金额
    private Integer loanInterestRate;                   // 贷款利率
    private String disbursementDate;                    // 放款时间
    private String disbursementEntity = "HYQ";        // 放款主体
    private String isSettled = "否";                    // 是否结清
    private Integer overduePeriods;                     // 当前逾期期数
    private Integer remainingPrincipal;                 // 未还本金
    private Integer remainingInterest;                  // 未还利息
    private Integer remainingServiceFee = 0;            // 未还服务费
    private Integer remainingOverdueInterest = 0;       // 未还逾期利息
    private Integer remainingTotalAmount;               // 未还总金额
    private Integer overdueDays;                        // 逾期天数
    private Integer remainingPrincipalNotDue;           // 未到期本金
    private Integer totalSubstitutionAmount = 0;        // 代偿总金额
    private String bankCardReservedNumber;              // 银行卡预留号码
    private String bankCardNumber;                      // 银行卡号
    private Date bindCardDate;                          // 绑卡时间
    private String bankName;                            // 所属银行
    private String loanStartDate;                       // 借款起始日
    private String loanEndDate;                         // 借款截止日
    private Integer monthlyRepaymentDate;               // 每月还款日
    private Integer withdrawalAmount;                   // 提现金额
    private String withdrawalDate;                      // 提现日期
    private String repaymentMethod = "等额本金";         // 还款方式
    private String loanPlatform = "HYQ";            // 借款平台
    private String loanPlatformName = "快贷";            // 借款平台名称
    private String currentCreditorEntity = "HYQ";     // 当前债权主体
    private String assetParty = "HYQ";                // 资产方
    private String overdueInterestRate = "10%";            // 逾期利息费率
    private String investorCount = "1000";              // 投资人数量
    private String disbursementEntityName = "挖财";      // 放款主体名称

    public LoanInfo(int loanTerm) {
        if (loanTerm > 12 || loanTerm < 0) {
            throw new RuntimeException("借款产品的期限不能大于12小于0");
        } else {
            this.loanTerm = loanTerm;
            this.loanAmount = GenerationHandler.generateRandomInt(100, 999) * loanTerm;
            this.loanInterestRate = 12;
            this.disbursementDate = DateHandler.generateRandomDateString();
            this.overduePeriods = loanTerm;
            this.remainingServiceFee = loanTerm * 10;
            this.remainingOverdueInterest = loanTerm * 10;
            this.remainingPrincipal = loanAmount;
            this.remainingInterest = loanAmount * loanInterestRate / 100;
            this.remainingTotalAmount = remainingPrincipal + remainingInterest + remainingServiceFee + remainingOverdueInterest;
            this.overdueDays = DateHandler.daysBetween(disbursementDate) - 30;
            this.remainingPrincipalNotDue = 0;
            this.loanStartDate = disbursementDate;
            this.loanEndDate = DateHandler.calculateDateAfterMonths(disbursementDate, loanTerm);
            this.monthlyRepaymentDate = DateHandler.getDayOfMonthFromString(disbursementDate);
            this.withdrawalAmount = loanAmount;
            this.withdrawalDate = disbursementDate;
        }
    }

}
