package TO.Amc_wac_loan_goblin;

import org.testng.annotations.Test;

import Utils.JDBCUtil;
import lombok.Data;

@Data
public class strategy_dict extends amc_wac_loan_goblin_lib {
	
	//varchar(64) NOT NULL COMMENT 字典编码,
	public String dict_code ;
	//varchar(64) NOT NULL COMMENT 字典名称,
	public String dict_name ;
	//varchar(32) NOT NULL COMMENT 字典类型,
	public String dict_type ;
	//int(10) NOT NULL COMMENT 顺序,
	public String order_no ;
	//smallint(1) NOT NULL DEFAULT 1 COMMENT 是否启用，1-启用 0-未启用,
	public String enable ;
	//varchar(100) DEFAULT NULL COMMENT 备注,
	public String remark ;
	
	
	@Test()
	public static void createOne() {
		 strategy_dict a = new strategy_dict();
		 a.setDict_code("EXPIRE");
		 a.setDict_name("田园");
		 a.setDict_type("LOAN_STATUS");
		 a.setOrder_no("0");
		 a.setEnable("1");
		 a.setRemark("yes");
		 JDBCUtil.insert(a);
	}
	
	@Test(enabled=false)
	public static void update() {
		strategy_dict a = new strategy_dict();
		a.setRemark("贷款状态");
		strategy_dict b = new strategy_dict();
		b.setRemark("映射成功");
		JDBCUtil.update(b, a);
		
		
	}
}
