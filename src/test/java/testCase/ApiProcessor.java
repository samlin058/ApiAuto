package testCase;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utils.AssertResult;
import Utils.ExcelUtil;
import Utils.HttpUtil2;
import Utils.JsonUtil;
import Utils.SQLCheckUtil;
import Utils.StringUtil;
import config.ApiInfoConfig;
import config.ApiParamsConfig;

public class ApiProcessor extends BaseTester {
    
	@Test(dataProvider = "getDatas")
	public void apiProcessor(String caseId, String apiId, String requestDataStr, String headData,
			String expectedResponseData, String preValidateSql, String afterValiDateSql) {
		// TODO 前置数据准备

		// 1、前置sql验证
		boolean preSqlcheckResult = SQLCheckUtil.dataValidata(caseId, preValidateSql, 9);
		// 2、请求接口的类型
		String type = ApiInfoConfig.getTpyeByApiId(apiId);
		// 3、接口的地址
		String url = ApiInfoConfig.getUrlByApiId(apiId);
		// 4、请求参数
		Map<String, Object> params = StringUtil.parseMap(requestDataStr);
		// 5、获取请求头
		Map<String, Object> headMap = JsonUtil.jsonStrToMap(headData);
		// 6、发包得到响应结果
		String resultStr = HttpUtil2.request(type, url, params, headMap);
		// 7、后置验证
		boolean afterSqlcheckResult = SQLCheckUtil.dataValidata(caseId, afterValiDateSql, 11);
		// 8、断言结果是否通过
		Assert.assertTrue(AssertResult.isPass(resultStr, expectedResponseData, preSqlcheckResult, afterSqlcheckResult));
		// 9、收集每次执行结果，并一次性写回excel
		ExcelUtil.writeResultToExcel(caseId, 7, resultStr);
	}

	@DataProvider
	public Object[][] getDatas() {
		return ApiParamsConfig.getParameters();
	}
}
