package pojo;

import java.util.List;
import java.util.Map;

import lombok.Data;
@Data
public class SQLChecker {
	/**
	 * sql的编号
	 */
	private String no; 
	
	/**
	 * sql语句
	 */
	private String sql;
	
	/**
	 * 期望结果
	 */
	private List<Map<String, String>> expectedResult;
    
	/**
	 * 库名
	 */
	private String  database  ;
	
}
