package pojo;

import lombok.Data;

@Data
public class Repayment {
	
	private String aid;
	private String periods;
	private String repaymentTime;
	private String repaymentPrincipal;
	private String interest;
	private String penaltyInterest;
	private String platformServiceFee;
	private String repaymentOfPrincipal;
	private String interestPaid;
	private String penaltyInterestRepaid;
	private String platformServiceFeeRepaid;	
	private String reductionMoney;
	private String repaymentStatus;
	
}
