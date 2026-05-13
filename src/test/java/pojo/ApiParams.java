package pojo;

import lombok.Data;

@Data
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
	//(请求头数据)
	private String headData;
	@Override
	public String toString() {
		return "ApiParams [caseId=" + caseId + ", apiId=" + apiId + ", caseName=" + caseName + ", requestData="
				+ requestData + ", expectedResponseData=" + expectedResponseData + ", actualResponseData="
				+ actualResponseData + ", preValidataSql=" + preValidataSql + ", preValidataResult=" + preValidataResult
				+ ", afterValidataSql=" + afterValidataSql + ", afterValidataResult=" + afterValidataResult
				+ ", headData=" + headData + "]";
	}
    
}
