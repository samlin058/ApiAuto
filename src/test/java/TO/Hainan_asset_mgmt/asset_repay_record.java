package TO.Hainan_asset_mgmt;

import org.testng.annotations.Test;

import Utils.GeneratorUtil;
import Utils.JDBCUtil;
import lombok.Data;

@Data
public class asset_repay_record extends Hainan_asset_mgmt_lib {
	
	// varchar(64) DEFAULT NULL COMMENT '还款记录id',
	public String repay_id ;
	// varchar(64) NOT NULL COMMENT '资产id',
	public String asset_id ;
	// varchar(64) DEFAULT NULL COMMENT '处置方CODE',
	public String disposer_code ;
	// varchar(32) DEFAULT NULL COMMENT '批次号',
	public String assign_batch_no ;
    // varchar(32) DEFAULT NULL COMMENT '逾期阶段',
	public String phase ;
	// varchar(32) DEFAULT NULL COMMENT '还款月份',
	public String repaid_month ;
	// datetime DEFAULT NULL COMMENT '还款时间',	
	public String repaid_time ;
	// decimal(20,2) DEFAULT NULL COMMENT '还款金额',
	public String repay_amt ;
	// decimal(20,2) DEFAULT NULL COMMENT '入账金额',
	public String entry_amt ;
	// varchar(32) DEFAULT NULL COMMENT '还款账类型，PAID:实还，REDUCE:减免，AUTO_REDUCE:自动减免',
	public String repay_type ;
	// varchar(32) DEFAULT NULL COMMENT '案件资产状态:（NORMAL-正常;OVERDUE-逾期;REPAY_AL-结清）',
	public String asset_status ;
	// tinyint(1) DEFAULT '1' COMMENT '记录是否有效(1有效，0无效)，逻辑删除标识',
	public String active ;
	// int(11) DEFAULT NULL COMMENT '逾期天数',
	public String overdue_days ;
	// bigint(20) DEFAULT NULL COMMENT '委案金额 单位是分',
	public String assign_amt ;
	
	@Test
	public static void insertOne() {
		asset_repay_record item = new asset_repay_record();
		item.setId(GeneratorUtil.getRandomNum(11000000)+""+"");
		item.setRepay_id(GeneratorUtil.getRandomNum(50000)+"");
		item.setAsset_id("1596959259501359104");
		item.setDisposer_code("hainan");
		item.setAssign_batch_no("20220219_001");
		//item.setPhase();
		item.setRepaid_month("2022-02");
		item.setRepaid_time("2022-02-04 10:00:20");
		item.setRepay_amt("100000.00");
		item.setEntry_amt("100000.00");
		item.setRepay_type("PAID");
		item.setAsset_status("OVERDUE");
		item.setActive("1");
		item.setOverdue_days("250");
		item.setAssign_amt("900000");
		JDBCUtil.insert(item);
	}
}
