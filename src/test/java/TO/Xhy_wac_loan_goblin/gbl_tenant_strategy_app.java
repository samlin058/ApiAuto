package TO.Xhy_wac_loan_goblin;

import Utils.JDBCUtil;
import lombok.Data;

@Data
public class gbl_tenant_strategy_app extends Xhy_wac_loan_goblin_lib {
	
	// '主键'
	public String id;
	// '租户对应策略系统bu表主键ID'
	public String tenant_strategy_bu_id;
	// '策略CODE e.g. SMS（短信策略）、ASSIGN（分案策略）'
	public String strategy_code;
	// '策略组'
	public String strategy_app;
	// '记录是否有效(1有效，0无效)，逻辑删除标识',
	public String active;
	
	public static void main(String[] args) {
		gbl_tenant_strategy_app a = new gbl_tenant_strategy_app();
		JDBCUtil.query(a);
	}
}
