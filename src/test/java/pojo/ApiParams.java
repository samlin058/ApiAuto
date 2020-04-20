package pojo;

public class ApiParams {
	/**
	 * 接口参数pojo类
	 */
	//(用例编号)
	private String caseId;
	//(接口编号)
	private String apiId;
	//(用例名称)
	private String caseName;
	//(接口请求参数)
	private String requestData;
	//(期望响应数据)
	private String expectedResponseData;
	//(实际响应数据)
	private String actualResponseData;
	//(接口执行前的脚本验证)
	private String preValidataSql;
	//(接口执行前数据库验证结果)
	private String preValidataResult;
	//(接口执行后的脚本验证)
	private String afterValidataSql;
	//(接口执行后数据库验证结果)
	private String afterValidataResult;
	private String isHead;
	private String headData;

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getRequestData() {
		return requestData;
	}

	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}

	public String getExpectedResponseData() {
		return expectedResponseData;
	}

	public void setExpectedResponseData(String expectedResponseData) {
		this.expectedResponseData = expectedResponseData;
	}

	public String getActualResponseData() {
		return actualResponseData;
	}

	public void setActualResponseData(String actualResponseData) {
		this.actualResponseData = actualResponseData;
	}

	public String getPreValidataSql() {
		return preValidataSql;
	}

	public void setPreValidataSql(String preValidataSql) {
		this.preValidataSql = preValidataSql;
	}

	public String getPreValidataResult() {
		return preValidataResult;
	}

	public void setPreValidataResult(String preValidataResult) {
		this.preValidataResult = preValidataResult;
	}

	public String getAfterValidataSql() {
		return afterValidataSql;
	}

	public void setAfterValidataSql(String afterValidataSql) {
		this.afterValidataSql = afterValidataSql;
	}

	public String getAfterValidataResult() {
		return afterValidataResult;
	}

	public void setAfterValidataResult(String afterValidataResult) {
		this.afterValidataResult = afterValidataResult;
	}

	public String getIsHead() {
		return isHead;
	}

	public void setIsHead(String isHead) {
		this.isHead = isHead;
	}

	public String getHeadData() {
		return headData;
	}

	public void setHeadData(String headData) {
		this.headData = headData;
	}

	@Override
	public String toString() {
		return "ApiParams [caseId=" + caseId + ", apiId=" + apiId + ", caseName=" + caseName + ", requestData="
				+ requestData + ", expectedResponseData=" + expectedResponseData + ", actualResponseData="
				+ actualResponseData + ", preValidataSql=" + preValidataSql + ", preValidataResult=" + preValidataResult
				+ ", afterValidataSql=" + afterValidataSql + ", afterValidataResult=" + afterValidataResult
				+ ", isHead=" + isHead + ", headData=" + headData + "]";
	}

}
