package TO.Xhy_wac_loan_goblin;

public class gbl_tenant extends Xhy_wac_loan_goblin_lib {
	//varchar(50) DEFAULT NULL COMMENT '名称',
	public String name ;
	//varchar(50) DEFAULT NULL COMMENT '编码code',
	public String code ;
	//tinyint(1) DEFAULT '1' COMMENT '记录是否有效(1有效，0无效)，逻辑删除标识',
	public String active ;
	//varchar(64) NOT NULL DEFAULT '' COMMENT '领域(区分西湖上城)',
	public String domain ;
}
