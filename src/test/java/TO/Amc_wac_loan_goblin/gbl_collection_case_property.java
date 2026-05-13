package TO.Amc_wac_loan_goblin;

import org.testng.annotations.Test;

import Utils.JDBCUtil;
import lombok.Data;

@Data
public class gbl_collection_case_property extends amc_wac_loan_goblin_lib {
	// int(20) unsigned DEFAULT NULL COMMENT '案件id',
	public String collection_id;
	//varchar(32) DEFAULT NULL COMMENT 'key',
	public String name ;
	//varchar(32) DEFAULT NULL COMMENT 'value',
	public String value ;
	//datetime DEFAULT NULL COMMENT '失效时间',
	public String expire_date ;
	// tinyint(1) DEFAULT '1' COMMENT '记录是否有效(1有效，0无效)，逻辑删除标识',
	public String active ;
	//datetime DEFAULT NULL COMMENT '生效时间',
	public String effective_date ;
    //varchar(32) DEFAULT NULL COMMENT '标签类型',
	public String type ;
	// varchar(512) DEFAULT NULL COMMENT '备注',
	public String reason ;
	//int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
	public String version ;
	@Test
	public static void createOne() {
		gbl_collection_case_property a = new gbl_collection_case_property();
		a.setCollection_id("93848");
		a.setName("complaint");
		a.setValue("SUCCESS");
		a.setExpire_date("2022-10-27 00:00:00");
		a.setActive("1");
		a.setEffective_date("2022-1-27 00:00:00");
		a.setVersion("1");
		JDBCUtil.insert(a);
	}
}
