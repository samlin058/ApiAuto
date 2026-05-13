package TO.Wac_loan_medivh;

import org.testng.annotations.Test;

import Utils.JDBCUtil;
import lombok.Data;

@Data
public class authorization extends Wac_loan_medivh_lib {

	// varchar(32) NOT NULL COMMENT '账户',
	public String account;
	//varchar(16) NOT NULL COMMENT '授权级别：BU,APP',
	public String auth_level ;
	//bigint(20) unsigned NOT NULL COMMENT '被授权的id，是business,application的主键id',
	public String auth_id ;
	// varchar(16) NOT NULL COMMENT '授权类型：READ,WRITE',
	public String auth_type;
	// varchar(256) DEFAULT NULL COMMENT '授权描述',
	public String description;
	//tinyint(1) DEFAULT NULL COMMENT '逻辑删除标识',
	public String active ;
	
    @Test 
	public static void createOne() {
		authorization authorization = new authorization();
		authorization.setAccount("luandao");
		authorization.setAuth_level("BU");
		authorization.setAuth_id("48");
		authorization.setAuth_type("WRITE");
		authorization.setActive("1");
	    JDBCUtil.insert(authorization);
	}
}
