package testCase;

import pojo.GoblinData;
import pojo.Repayment;

import java.util.ArrayList;

public class gblData {
	
	final static String sourcePath = "/goblin_loan_info_1.xlsx";
	final static String targetPath = "target/goblin_loan_info.xlsx";

	/**
	 * 生成测试数据
	 * @param args void
	 *
	 * @author       luandao
	 * @Date         2020-10-28
	 *
	 */
	public static void main(String[] args) {
		createGoblinDataTodays();
		// createGoblinDataByNum(400);

	}

	/**
	 * 根据 num 生成指定数量用户
	 * @Case  根据 num 生成数据
	 * @author  luandao
	 * @Date    2020-09-09
	 *
	 */
	private static void createGoblinDataByNum(int num) {
		// 生成用户数据
		newMan(num);
		// 写入 Excel
	//	writeDataToExcel(sourcePath, targetPath);
	}

	private static void createGoblinDataTodays() {

		// 写入 Excel
	//	writeDataToExcelOnlyData("/goblin_loan_info.xlsx", targetPath);
	}

	// 收集用户信息 list
	static ArrayList<GoblinData> manList = new ArrayList<GoblinData>();
	// 收集用户还款计划 list
	static ArrayList<Repayment> repaymentList = new ArrayList<Repayment>();

	/**
	 * 
	 * @Case 生成用户数据
	 * @author  luandao
	 * @Date    2020-09-09
	 *
	 */
	private static void newMan(int manNum) {
		for (int i = 0; i < manNum; i++) {
			GoblinData man = new GoblinData();
			man.setAid(20200907001L + i + "");
			man.setLoanType("WC_XYJ");
			man.setApplyMoney("6000");
			man.setApplicationDate("2020/1/24");
			man.setAmountApproved("6000");
			man.setApprovalDate("2020/1/24");
			man.setContractAmount("6000");
			man.setLoanAmount("6000");
			man.setLoanDate("2020/1/24");
			man.setRepaymentMethod("month");
			man.setPhoneNum(13916810001L + i + "");
			man.setCustomerName("小明" + i);
			man.setSex("女");
			man.setIdNum(110101199003070070L + i + "");
			manList.add(man);
		}

		for (int i = 0; i < manNum; i++) {
			String Aid = manList.get(i).getAid();
			Repayment repayment = new Repayment();
			repayment.setAid(Aid);
			repayment.setPeriods("1");
			repayment.setRepaymentTime("2020-02-24 16:00:00");
			repayment.setRepaymentPrincipal("6000.00");
			repayment.setInterest("10.00");
			repayment.setPenaltyInterest("10.00");
			repayment.setPlatformServiceFee("10.00");
			repayment.setRepaymentOfPrincipal("0.00");
			repayment.setInterestPaid("0.00");
			repayment.setPenaltyInterestRepaid("0.00");
			repayment.setPlatformServiceFeeRepaid("0.00");
			repayment.setReductionMoney("0.00");
			repayment.setRepaymentStatus("UNPAID");
			repaymentList.add(repayment);
		}
	}

	// 旧版 Excel 写入方法已下线，保留历史逻辑请查看提交历史。
}
