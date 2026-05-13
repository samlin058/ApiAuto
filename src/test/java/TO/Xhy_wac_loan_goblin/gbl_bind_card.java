package TO.Xhy_wac_loan_goblin;

import Utils.JDBCUtil;
import lombok.Data;

@Data
public class gbl_bind_card extends Xhy_wac_loan_goblin_lib {
	//int(20) unsigned NOT NULL COMMENT 'loanId',
	public String loan_id ;
	// varchar(32) DEFAULT NULL COMMENT '开户名',
	public String name ;
	//varchar(32) DEFAULT NULL COMMENT '银行预留手机号',
	public String mobile ;
	//int(20) DEFAULT NULL COMMENT '银行ID',
	public String bank_id ;
	// varchar(32) DEFAULT NULL COMMENT '银行卡卡号',
	public String bank_card_no ;
	//varchar(32) DEFAULT NULL COMMENT '银行卡开户行',
	public String bank_name ;
	//tinyint(4) DEFAULT '1' COMMENT '绑定优先级',
	public String priority ;
	//tinyint(1) DEFAULT '1' COMMENT '记录是否有效(1有效，0无效)，逻辑删除标识',
	public String active ;
	
	public static void main(String[] args) {
		gbl_bind_card gbc = new gbl_bind_card();
		gbc.setLoan_id("36624895");
		gbc.setName("鸾刀");
		gbc.setMobile("13916815214");
		gbc.setBank_id("1");
		gbc.setBank_card_no("6013827215181074402");
		gbc.setBank_name("中国银行");
		gbc.setPriority("1");
		gbc.setActive("1");
		JDBCUtil.insert(gbc);
	}
}
