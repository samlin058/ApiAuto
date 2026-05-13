package TO.Hainan_compass;

import Utils.JDBCUtil;
import lombok.Data;

@Data
public class asset_quality_inspection_record extends Hainan_compass_lib {

	// varchar(64) NOT NULL COMMENT '资产id',
	public String asset_id;
	// varchar(64) NOT NULL COMMENT '原业务号',
	public String order_no;
	// varchar(32) NOT NULL COMMENT '账号',
	public String account;
	// varchar(32) NOT NULL COMMENT '催收员名称',
	public String agent_name;
	// varchar(32) NOT NULL COMMENT '资产包编号',
	public String asset_pkg_no;
	// varchar(64) DEFAULT NULL COMMENT '资产包名称',
	public String asset_pkg_name;
	// varchar(64) NOT NULL COMMENT '处置方编码',
	public String disposer_code;
	// varchar(64) NOT NULL COMMENT '处置方名称',
	public String disposer_name;
	// varchar(64) NOT NULL COMMENT '处置批次号',
	public String assign_batch_no;
	// timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '拨打时间',
	public String call_time;
	// int(10) DEFAULT '0' COMMENT '通话时长，单位秒',
	public String talk_time;
	// varchar(32) NOT NULL COMMENT '通话连接ID',
	public String conn_id;
	// varchar(255) DEFAULT NULL COMMENT '录音地址',
	public String record_url;
	// varchar(8) NOT NULL COMMENT '质检类型(1-通话时长小于5秒；2-催收员声道没有声；3-触发敏感词)',
	public String inspection_type;
	// tinyint(1) DEFAULT '1' COMMENT '记录是否有效(1有效，0无效)，逻辑删除标识',
	public String active;
	// varchar(240) DEFAULT NULL COMMENT '触发质检原因',
	public String inspection_reason;
   
	
	public void addOne() {
		asset_quality_inspection_record item = new asset_quality_inspection_record();
		item.setAsset_id("1668665184133885952");
		item.setOrder_no("LD20220810003");
		item.setAccount("luandao");
		item.setAgent_name("鸾刀");
		item.setAsset_pkg_no("20220701_001");
		item.setAsset_pkg_name("鸾刀日常测试");
		item.setDisposer_code("XIHU_LDXHCS");
		item.setDisposer_name("鸾刀西湖催收");
		item.setAssign_batch_no("20220810_006");
		item.setCall_time("2022-04-27 09:05:56");
		item.setTalk_time("10");
		item.setConn_id("linkup335821450");
		item.setRecord_url("/outbound/goblin_yhxd_queue_empty/2022/04/27/09/linkup335821450a_9683000536_706021_152********.wav");
		item.setInspection_type("3");
		item.setActive("1");
		item.setInspection_reason("");
		item.setCreated_by("luandao");
 		item.setUpdated_by("luandao");	
 		
 		JDBCUtil.insert(item);
	}
	public static void main(String[] args) {
		asset_quality_inspection_record item = new asset_quality_inspection_record();
		item.setAsset_id("1668665184133885952");
		item.setOrder_no("LD20220810003");
		item.setAccount("luandao");
		item.setAgent_name("鸾刀");
		item.setAsset_pkg_no("20220701_001");
		item.setAsset_pkg_name("鸾刀日常测试");
		item.setDisposer_code("XIHU_LDXHCS");
		item.setDisposer_name("鸾刀西湖催收");
		item.setAssign_batch_no("20220810_006");
		item.setCall_time("2022-04-27 09:05:56");
		item.setTalk_time("10");
		item.setConn_id("linkup335821450");
		item.setRecord_url("/outbound/goblin_yhxd_queue_empty/2022/04/27/09/linkup335821450a_9683000536_706021_152********.wav");
		item.setInspection_type("3");
		item.setActive("1");
		item.setInspection_reason("");
		item.setCreated_by("luandao");
 		item.setUpdated_by("luandao");	
 		
 		JDBCUtil.insert(item);
	}
}
