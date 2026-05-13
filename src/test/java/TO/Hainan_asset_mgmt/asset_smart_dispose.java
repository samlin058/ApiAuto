package TO.Hainan_asset_mgmt;


import Utils.JDBCUtil;
import lombok.Data;

@Data
public class asset_smart_dispose  extends Hainan_asset_mgmt_lib{
	//自增主键
	public String  id ;
	//pre_asset_pkg表id
	public String  pre_asset_pkg_id ;
	//处置方代码
	public String  disposer_code ;
	//处置方名称
	public String  disposer_name ;
	//原订单号
	public String  order_no ;
	//业务范围
	public String  biz_scope ;
	//是否留案
	public String  is_reserved ;
	//状态
	public String  status ;
	
	public static void main(String[] args) {
		asset_smart_dispose item = new asset_smart_dispose();
		item.setPre_asset_pkg_id("1595856365516234752");
		item.setDisposer_code("YongHui");
		item.setDisposer_name("永辉");
		item.setOrder_no("LD20220321002");
		item.setBiz_scope("PHONE_COLLECTION");
		item.setIs_reserved("0");
		item.setStatus("TODO");
		JDBCUtil.insert(item);
	}

}
