package Service.Pojo;

import Service.Util.Universal.DateHandler;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RepaymentInfo {

    private Integer period;                               // 期次
    private String dueDate;                               // 应还款日期
    private String status = "未结清";                      // 状态
    private Integer dueAmount;                            // 本期应还款
    private Integer duePrincipal;                         // 本期应还本金
    private Integer dueInterest;                          // 本期应还利息
    private Integer dueServiceFee;                       // 本期应还服务费
    private Integer dueOverdueInterest;                   // 本期应还逾期利息
    private String actualRepaymentDate = "";              // 本期实还款日期
    private Integer actualRepaymentAmount = 0;            // 本期实还款
    private Integer actualRepaymentPrincipal = 0;         // 本期实还本金
    private Integer actualRepaymentInterest = 0;          // 本期实还利息
    private Integer actualRepaymentServiceFee = 0;        // 本期实还服务费
    private Integer actualRepaymentOverdueInterest = 0;   // 本期实还逾期利息

    /**
     * 根据产品生产对应的还款计划
     *
     * @param loanInfo 产品信息
     * @return 还款计划list
     */
    public static List<RepaymentInfo> getRepaymentInfoList(LoanInfo loanInfo) {
        List<RepaymentInfo> repaymentInfoList = new ArrayList<>();
        int loanTerm = loanInfo.getLoanTerm();
        Integer currentPeriodPrincipal = loanInfo.getLoanAmount() / loanTerm; //本期应还本金
        Integer currentPeriodInterest = loanInfo.getLoanAmount() * loanInfo.getLoanInterestRate() / 100 / loanTerm; //本期应还利息
        for (int i = 1; i <= loanTerm; i++) {
            RepaymentInfo repaymentInfo = new RepaymentInfo();
            repaymentInfo.setDueServiceFee(loanInfo.getRemainingServiceFee() / loanTerm);
            repaymentInfo.setDueOverdueInterest(loanInfo.getRemainingOverdueInterest() / loanTerm);
            repaymentInfo.setPeriod(i);
            repaymentInfo.setDueDate(DateHandler.calculateDateAfterMonths(loanInfo.getDisbursementDate(), i));
            repaymentInfo.setDueAmount(currentPeriodPrincipal + currentPeriodInterest + repaymentInfo.getDueServiceFee() + repaymentInfo.getDueOverdueInterest());
            repaymentInfo.setDuePrincipal(currentPeriodPrincipal);
            repaymentInfo.setDueInterest(currentPeriodInterest);
            repaymentInfoList.add(repaymentInfo);
        }
        return repaymentInfoList;
    }


}
