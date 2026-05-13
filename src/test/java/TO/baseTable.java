package TO;

import Utils.JDBCUtil;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class baseTable {
	
	// 逻辑运算符：or / and
	private String logicalSymbol;
    //
	private String sql;
	//
	private String libraryStr;
	// 主键
	public String id ; 
	// 创建时间
	public String created_time;
	// 创建人
	public String created_by;
	// 更新时间
	public String updated_time;
	// 更新人
	public String updated_by;
	
	public static void main(String[] args) {
		baseTable a = new baseTable();
//		a.setSql("SELECT * FROM gbl_collection_case WHERE id = 93850");
//		a.setLibraryStr("amc_wac_loan_goblin");
		a.setLibraryStr("amc_wac_loan_goblin");
		a.setSql("SELECT * FROM gbl_assignment WHERE collection_id IN (SELECT id FROM gbl_collection_case WHERE loan_id IN (SELECT id FROM gbl_loan WHERE channel ='4_wacao' )) ORDER BY collection_id DESC;");
		List<Map<String, String>> b = JDBCUtil.queryForSql(a);
		System.out.println(b);
//		JDBCUtil.queryForSql("amc_wac_loan_goblin", "SELECT * FROM strategy_dict WHERE remark LIKE '%映射%'");
		
	}
    
}
