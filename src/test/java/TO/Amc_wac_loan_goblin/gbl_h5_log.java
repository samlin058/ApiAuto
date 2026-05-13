package TO.Amc_wac_loan_goblin;

import org.testng.annotations.Test;

import Utils.JDBCUtil;
import lombok.Data;

@Data
public class gbl_h5_log extends amc_wac_loan_goblin_lib {
	//varchar(32) DEFAULT NULL COMMENT '用户名',
	public String name ;
	//varchar(32) DEFAULT NULL COMMENT '身份证号',
	public String idno ;
    //varchar(32) DEFAULT NULL COMMENT '手机',
	public String mobile ;
	//int(20) unsigned DEFAULT NULL COMMENT 'gbl_loan表ID',
	public String loan_id ;
	// varchar(128) DEFAULT NULL COMMENT 'url',
	public String url;
	// tinyint(1) DEFAULT '1' COMMENT '记录是否有效(1有效，0无效)，逻辑删除标识',
	public String active;
	//varchar(64) DEFAULT NULL COMMENT '领域(区分西湖上城)',
	public String domain ;
	//varchar(32) DEFAULT NULL COMMENT '日志类型',
	public String log_type ;
	
	@Test
	public static void createOne() {
		gbl_h5_log a = new gbl_h5_log();
		a.setName("16600658606");
		a.setIdno("469026196103039608");
		a.setMobile("16600658606");
		a.setLoan_id("93845");
		a.setUrl("http://127.0.0.1:8088/loan/pay");
		a.setActive("1");
		JDBCUtil.insert(a);
	}
}
