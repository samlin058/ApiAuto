package TO.Amc_wac_loan_goblin;

import Utils.JDBCUtil;
import lombok.Data;

@Data
public class gbl_loan_supplement extends amc_wac_loan_goblin_lib {
	//varchar(64) NOT NULL COMMENT 'AID',
	public String application_id ;
	//varchar(128) DEFAULT NULL COMMENT '商品类别',
	public String commodity_type ;
	//varchar(128) DEFAULT NULL COMMENT '商品品牌',
	public String commodity_brand ;
	//varchar(128) DEFAULT NULL COMMENT '商品型号',
	public String commodity_model ;
	//varchar(128) DEFAULT NULL COMMENT '商品2类别',
	public String commodity_type_2 ;
	//varchar(128) DEFAULT NULL COMMENT '商品2品牌',
	public String commodity_brand_2 ;
	//varchar(128) DEFAULT NULL COMMENT '商品2型号',
	public String commodity_model_2 ;
	//tinyint(4) NOT NULL DEFAULT '1' COMMENT '记录是否有效(1有效，0无效)，逻辑删除标识',
	public String active ;
	//varchar(512) DEFAULT NULL COMMENT '车牌号码',
	public String license_plate_no ;
	//varchar(512) DEFAULT NULL COMMENT '车辆类型',
	public String car_type ;
	//varchar(128) DEFAULT NULL COMMENT '商店名称',
	public String commodity_store_name ;
	//varchar(128) DEFAULT NULL COMMENT '商品价格',
	public String commodity_price ;
	
	public static void main(String[] args) {
		gbl_loan_supplement item = new gbl_loan_supplement();
		item.setActive("1");
		item.setApplication_id("LD20220331002");
		item.setCommodity_type("手机");
		item.setCommodity_brand("oppo");
		item.setCommodity_model("R11s");
		item.setCommodity_type_2("其他电子产品");
		item.setCommodity_brand_2("充电宝");
		item.setCommodity_model_2("*");
		JDBCUtil.insert(item);
	}

}
