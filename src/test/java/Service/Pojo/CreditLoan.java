package Service.Pojo;

import Service.Util.variable.GenerationHandler;
import lombok.Data;

import java.util.List;

@Data
public class CreditLoan {

    public String OrderNo;  // 资产编号

    public LoanInfo loanInfo; // 借款信息

    public CustomerInfo customerInfo; // 客户信息

    public List<EmergencyContacts> emergencyContactsList; // 紧急联系人信息

    public List<RepaymentInfo> repaymentInfoList; // 还款计划

    public List<RepaymentRecord> repaymentRecordList; // 还款记录

    public List<TransactionRecord> transactionRecordList; // 流水记录

    public List<ContractInfo> contractInfoList; //合同信息

    public List<BankCardInfo> bankCardInfoList; //银行卡信息


    public CreditLoan(String orderNo) {
        this.OrderNo = orderNo;
        this.loanInfo = new LoanInfo(GenerationHandler.generateRandomInt(7, 12));
        this.customerInfo = new CustomerInfo();
        this.emergencyContactsList= EmergencyContacts.getEmergencyContactsList();
        this.repaymentInfoList = RepaymentInfo.getRepaymentInfoList(loanInfo);
        this.bankCardInfoList = BankCardInfo.getBankCardInfoList(loanInfo, customerInfo);
    }

    /*public List<LoanRepayment> createRepayments() {
        Date now = new Date();
        Random r = new Random();
        Date loanTime = TimeUtils.addYear(now, -3);
        //int totalAmount = r.nextInt(9) + 1;
        // int totalAmountSum = totalAmount * 500000;   //本金
        // int totalMonth = r.nextInt(12) + 3;   // 贷款期数

        int totalMonth = getRandomAmount(1,12);
        int totalAmount = getRandomAmount(3,50);
        int totalAmountSum = totalAmount * 50000;

        double monthRate = 0.0065; // 月利率
        List<LoanRepayment> repayments = new ArrayList<>();
        BigDecimal monthRepayAmount = new BigDecimal(monthRepayAmount(totalAmountSum, monthRate, totalMonth));
        BigDecimal sumCapital = new BigDecimal(0);
        for (int i = 1; i <= totalMonth; i++) {
            //每月利息
            BigDecimal monthInterest = BigDecimal.valueOf((totalAmountSum - sumCapital.intValue()) * monthRate).setScale(0, BigDecimal.ROUND_HALF_UP);// getMonthInterest(baseAmount, monthRate, totalMonth, i, monthRepayAmount);
            BigDecimal pmtMaxFee = calPmtMaxFee(totalAmountSum, totalMonth);
            BigDecimal serviceFee = pmtMaxFee.subtract(monthRepayAmount);
            //每月本金，月供本金 = 月供 - 月供利息 ，这样算是因为 本金+利息≠每月应还
            BigDecimal monthCapital;
            if (i != totalMonth) {
                monthCapital = monthRepayAmount.subtract(monthInterest);
                sumCapital = sumCapital.add(monthCapital);
            } else {
                //最后一期总金额 总本金-前几期本金之和
                monthCapital = new BigDecimal(totalAmountSum).subtract(sumCapital);
//                monthInterest = monthRepayAmount.subtract(monthCapital); //getBaseAmount(monthCapital.intValue(), monthRate);//最后一期利息=最后一期本金*月利率
            }
            LoanRepayment repayment = new LoanRepayment();
            repayment.setRepayAmount((long) monthCapital.intValue()); //本金
            repayment.setRepayedAmount(0);
            repayment.setRepayPlanTime(getRepayPlanTime(i, loanTime));
            repayment.setInterestAmount((long) monthInterest.intValue());  //利息
            repayment.setServiceAmount((long) serviceFee.intValue());  // 服务费
            repayment.setInstallment(i);
            repayment.setLoanAmt(totalAmountSum);
            repayment.setLoanTime(loanTime);
            repayment.setPeriodIndex(totalMonth);
            repayment.setRepayPenaltyFeeAmt(10000);
            repayments.add(repayment);
        }
        return repayments;
    }*/

}
