package TO.Wac_loan_medivh;

import org.testng.annotations.Test;

import Utils.JDBCUtil;
import lombok.Data;

@Data
public class business extends Wac_loan_medivh_lib {
	// bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键'
	public String id;
	// varchar(128) DEFAULT NULL COMMENT 'code'
	public String code;
	// varchar(128) NOT NULL COMMENT '名称'
	public String name;
	//varchar(256) DEFAULT NULL COMMENT '描述'
	public String description ;
	//tinyint(1) DEFAULT NULL COMMENT '逻辑删除标识'
	public String active ;
	
	@Test
	public void createNewCount() {
		business business = new business();
		business.setName("永辉分案");
		business.setCode("gbl-yonghui-assign");
		business.setActive("1");
		JDBCUtil.insert(business);
	}
}
