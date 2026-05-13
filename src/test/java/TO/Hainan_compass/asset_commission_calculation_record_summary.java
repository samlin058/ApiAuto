package TO.Hainan_compass;

public class asset_commission_calculation_record_summary extends Hainan_compass_lib {
	//varchar(32) NOT NULL COMMENT '月份',
	public String month ;
	//varchar(32) DEFAULT NULL COMMENT '处置方代码',
	public String disposer_code ;
	//varchar(64) DEFAULT NULL COMMENT '处置方名称',
	public String disposer_name ;
	//decimal(20,2) DEFAULT NULL COMMENT '实收金额 单位元',
	public String repay_amt ;
	//bigint(20) DEFAULT '0' COMMENT '案件数量',
	public String case_count ;
	//decimal(20,2) DEFAULT NULL COMMENT '佣金金额',
	public String commission_amt ;
	//tinyint(1) DEFAULT '1' COMMENT '记录是否有效(1有效，0无效)，逻辑删除标识'
	public String active ;
	
	
	
}
