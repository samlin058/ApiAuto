package TO.Wac_loan_medivh;

import org.testng.annotations.Test;

import Utils.JDBCUtil;
import lombok.Data;

@Data
public class factor extends Wac_loan_medivh_lib {
	
	// bigint(20) unsigned DEFAULT NULL COMMENT '业务线ID',
	public String bu_id ;
	//varchar(64) NOT NULL COMMENT '因子名字',
	public String name ;
	//varchar(64) NOT NULL COMMENT '因子代码',
	public String code ;
	// varchar(64) DEFAULT NULL COMMENT '描述',
	public String description;
	// varchar(64) DEFAULT NULL COMMENT '支持的操作符',
	public String supported_operators;
	//varchar(16) DEFAULT NULL COMMENT 'DATE-日期，STRING-字符串，NUMBER-数字，BOOLEAN-布尔值',
	public String threshold_type ;
	//text COMMENT '候选值json串',
	public String candidate_values; 
	//tinyint(1) DEFAULT NULL COMMENT '记录是否有效(1有效，0无效)，逻辑删除标识',
	public String active ;
	
	@Test
	public static void deleteOne() {
		factor factor = new factor();
		factor.setBu_id("48");
		JDBCUtil.delete(factor);
	}
}
