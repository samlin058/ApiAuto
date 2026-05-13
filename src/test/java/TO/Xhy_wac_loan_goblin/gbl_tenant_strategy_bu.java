package TO.Xhy_wac_loan_goblin;

import lombok.Data;


@Data
public class gbl_tenant_strategy_bu extends Xhy_wac_loan_goblin_lib {
	
	// '主键',
	private String id;
	// '租户ID',
	private String tenant_id;
	// '策略bu',
	private String strategy_bu;
	// '记录是否有效(1有效，0无效)，逻辑删除标识',
	private String active;
	
}
