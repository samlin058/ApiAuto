package pojo;

import java.util.List;
import java.util.Map;

import lombok.Data;


@Data
public class CheckResult {
	/**
	 * sql的编号
	 */
	String no;

	/**
	 * 实际的结果
	 */
	List<Map<String, String>> actualResult;

	/**
	 * 验证是否通过，success为通过，failure为失败
	 */
	String result;


	public CheckResult(String no, List<Map<String, String>> actualResult, String result) {
		super();
		this.no = no;
		this.actualResult = actualResult;
		this.result = result;
	}
	
	  

}
