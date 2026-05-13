package TO.Hainan_asset_mgmt;

import Utils.JDBCUtil;
import lombok.Data;

@Data
public class proof_request_record extends Hainan_asset_mgmt_lib {
	 //varchar(64) DEFAULT NULL COMMENT '文件名，不带后缀'
	 public String file_name ;
	 //varchar(64) DEFAULT NULL COMMENT '文件名'
	 public String attach_name ; 
	 //varchar(64) DEFAULT NULL COMMENT '文件类型'
	 public String attach_type ;
	 //varchar(10) DEFAULT NULL COMMENT '操作类型'
	 public String request_type ;
	 //varchar(64) DEFAULT NULL COMMENT '处置机构'
	 public String disposer_code ;
	 //varchar(64) DEFAULT NULL COMMENT '原订单号'
	 public String order_no ;
	 //varchar(64) DEFAULT NULL COMMENT '资产包编号'
	 public String asset_pkg_no ;
	 //varchar(64) DEFAULT NULL COMMENT '资产编号'
	 public String asset_id ;

    
     public static void insert() {
		proof_request_record item = new proof_request_record();
		item.setFile_name("律师函");
		item.setAttach_name("律师函.jpg");
		item.setAttach_type("LEGAL_LETTER");
		item.setRequest_type("PREVIEW");
		item.setDisposer_code("WEIWO_LDCS");
		item.setOrder_no("LD202209019002");
		item.setAsset_pkg_no("20220701_001");
		item.setAsset_id("1683095809435156480");
		JDBCUtil.insert(item);
	}
     
     public static void main(String[] args) {
    	 for (int i = 0; i < 1; i++) {
    		 insert();
		}
	}

	
}
