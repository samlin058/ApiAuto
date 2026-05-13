package TO.Hainan_asset_mgmt;

import Utils.GeneratorUtil;
import Utils.JDBCUtil;
import lombok.Data;

@Data
public class asset_dispose_sms extends Hainan_asset_mgmt_lib {

	// '主键'
	public String id;
	// 'loan_asset.id'
	public String asset_id;
	// '接收人'
	public String recipients;
	// '短信内容'
	public String content;
	// '短信模板中心business_id'
	public String business_id;
	// '短信平台id'
	public String sid;
	// '发送平台'
	public String channel;
	// '发送结果（0：黑名单，1：发送成功 2：发送失败）'
	public String status;
	// '错误码'
	public String err_code;
	// '错误码描述'
	public String err_msg;
	// '操作员'
	public String operator;
	// '发送方式'
	public String type;
	// '结论'
	public String conclusion;
	// '签名'
	public String sign;
	// '备注'
	public String remark;
	// '外部业务ID,对于goblin的interaction-ID//'
	public String asset_interaction_id;
	// '外部业务ID,对于goblin的sms_id'
	public String ext_bu_id;
	// '处置方code'
	public String disposer_code;
	// '1//' '记录是否有效(1有效，0无效)，逻辑删除标识//'
	public String active;

	public static void main(String[] args) {
		asset_dispose_sms sms = new asset_dispose_sms();
		sms.setExt_bu_id(GeneratorUtil.getRandomNum(4333333) + "");
		sms.setAsset_id(GeneratorUtil.getRandomNum(612112333) + "");
		sms.setAsset_interaction_id(GeneratorUtil.getRandomNum(1012312312) + "");
		sms.setDisposer_code("luandao1");
		sms.setActive("1");
		sms.setLogicalSymbol("and");
		asset_dispose_sms condition = new asset_dispose_sms();
		condition.setDisposer_code("YongHui");
		condition.setExt_bu_id("5");
		condition.setAsset_interaction_id("714814271");
		condition.setLogicalSymbol("and");
		JDBCUtil.update(sms, condition);
		JDBCUtil.delete(sms);
	}

}
