package TO.Hainan_compass;



import Utils.JDBCUtil;
import lombok.Data;

@Data
public class call_record_daily extends Hainan_compass_lib {

	// varchar(64) DEFAULT NULL COMMENT '处置方id',
	public String  disposer_id ;
	// varchar(64) NOT NULL COMMENT '处置方CODE',
	public String  disposer_code;
	// varchar(64) DEFAULT NULL COMMENT '处置方名称',
	public String  disposer_name ;
	// varchar(32) DEFAULT NULL COMMENT '批次号',
	public String  assign_batch_no;
	// date NOT NULL COMMENT '电话记录日期（yyyy-MM-dd）',
	public String  call_date ;
	// varchar(32) DEFAULT NULL COMMENT '打电话类型:自动语音外呼、预测外呼、手工电催、呼入 ',
	public String  call_type;
	// varchar(32) DEFAULT NULL COMMENT '连接状态',
	public String  connection_status;
	// varchar(255) DEFAULT NULL COMMENT '催收结果',
	public String  result ;
	// int(20) unsigned DEFAULT NULL COMMENT '次数',
	public String  count ;
	// tinyint(1) DEFAULT '1' COMMENT '记录是否有效(1有效，0无效)，逻辑删除标识',
	public String  active ;
    // varchar(32) NOT NULL COMMENT '业务范围',
	public String  biz_scope ;
	
	public static void main(String[] args) {
		call_record_daily data = new call_record_daily();
		data.setDisposer_id("1511325354483888128");
		data.setDisposer_code("YongHui");
		data.setDisposer_name("永辉");
		data.setAssign_batch_no("20220110_014");
		data.setCall_date("2022-01-10");
		data.setCall_type("PHONE_MANUAL");
	    data.setConnection_status("CONNECTED");
	    data.setResult("无人接听");
	    data.setBiz_scope("PHONE_COLLECTION");
	    data.setCount("10");
		data.setActive("1");
	    JDBCUtil.insert(data);
	}
	
}
