package TO.Amc_wac_loan_goblin;

import org.testng.annotations.Test;

import Utils.JDBCUtil;
import lombok.Data;

@Data
public class gbl_customer_tag extends amc_wac_loan_goblin_lib {
    //int(20) unsigned DEFAULT NULL COMMENT '客户ID',
	public String customer_id ;
	//varchar(64) DEFAULT NULL COMMENT '标签',
	public String tag ;
	//tinyint(1) DEFAULT '1' COMMENT '记录是否有效(1有效，0无效)，逻辑删除标识',
	public String active ;
	//varchar(1000) DEFAULT NULL COMMENT '原因',
	public String reason ;
	//int(20) DEFAULT NULL COMMENT 'interaction主键',
	public String interaction_id ;
	//varchar(32) DEFAULT NULL COMMENT '标签类型',
	public String type ;
	 
	@Test
	public static void cretedOne() {
		gbl_customer_tag a = new gbl_customer_tag();
		a.setCustomer_id("93627");
		a.setTag("0001");
		a.setActive("1");
		a.setInteraction_id("93841");
		a.setType("SENSITIVE");
		JDBCUtil.insert(a);
	}
}
