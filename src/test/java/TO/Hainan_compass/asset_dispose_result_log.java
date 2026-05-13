package TO.Hainan_compass;

import Utils.JDBCUtil;
import lombok.Data;

@Data
public class asset_dispose_result_log extends Hainan_compass_lib {
	
	// varchar(16) NOT NULL COMMENT '处置日期（yyyy-MM-dd）',
	public String dispose_date;
	// varchar(32) DEFAULT NULL COMMENT '处置方代码',
	public String disposer_code;
	// varchar(64) DEFAULT NULL COMMENT '处置方名称',
	public String disposer_name;
	// varchar(64) DEFAULT NULL COMMENT '资产id',
	public String asset_id;
	// varchar(32) DEFAULT NULL COMMENT '/*资产包编号*/ ',
	public String asset_pkg_no;
	// varchar(64) DEFAULT NULL COMMENT '外部业务id（还款记录ID）',
	public String biz_id;
	// varchar(32) DEFAULT NULL COMMENT '批次号',
	public String assign_batch_no;
	// varchar(64) DEFAULT NULL COMMENT '处置结果(处置中/处置完成)',
	public String dispose_result;
	// tinyint(1) DEFAULT '1' COMMENT '记录是否有效(1有效，0无效)，逻辑删除标识',
	public String active;
    
	public static void insertOne() {
		asset_dispose_result_log item = new asset_dispose_result_log();
		item.setDispose_date("");
		item.setDisposer_code("");
		item.setDisposer_name("");
		item.setAsset_id("");
		item.setAsset_pkg_no("");
		item.setBiz_id("");
		item.setAssign_batch_no("");
		item.setDispose_result("");
		item.setActive("");
		JDBCUtil.insert(item);
	}
}
