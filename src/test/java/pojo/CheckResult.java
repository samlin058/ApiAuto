package pojo;

import java.util.List;
import java.util.Map;

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

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<Map<String, String>> getActualResult() {
		return actualResult;
	}

	public void setActualResult(List<Map<String, String>> actualResult) {
		this.actualResult = actualResult;
	}

	@Override
	public String toString() {
		return "CheckResult [no=" + no + ", result=" + result + ", actualResult=" + actualResult + "]";
	}

	public CheckResult(String no, List<Map<String, String>> actualResult, String result) {
		super();
		this.no = no;
		this.result = result;
		this.actualResult = actualResult;
	}

}
