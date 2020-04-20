package testCase;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

import Util.AssertResult;
import Util.ExcelUtil;
import Util.HeadUtil;
import Util.HttpUtil;
import Util.SQLCheckUtil;
import config.ApiInfoConfig;
import config.ApiParamsConfig;

public class ApiProcessor extends BaseTester {
	
	@Test(dataProvider = "getDatas")
	public void apiProcessor(String caseId, String apiId, String requestDataStr, String expectedResponseData,
			String preValidateSql, String afterValiDateSql, String isHead, String headData) {
		// 1、前置sql验证
		SQLCheckUtil.dataValidata(caseId, preValidateSql, 8);
		// 2、请求接口的类型
		String type = ApiInfoConfig.getTpyeByApiId(apiId);
		// 3、接口的地址
		String url = ApiInfoConfig.getUrlByApiId(apiId);
		// 4、请求参数
		Map<String, String> params = (Map<String, String>) JSONObject.parse(requestDataStr);
		// 请求头
		Map<String, String> headMap = HeadUtil.handle(isHead, headData);
		// 5、发包得到响应结果
		String resultStr = HttpUtil.request(type, url, params, headMap);
		// 断言结果是否通过
		Assert.assertTrue(AssertResult.isPass(resultStr, expectedResponseData));
		// 6、收集每次执行结果，并一次性写回excel
		ExcelUtil.writeResultToExcel(caseId, 6, resultStr);
		// 7、后置验证
		SQLCheckUtil.dataValidata(caseId, afterValiDateSql, 10);
	}

	@DataProvider
	public Object[][] getDatas() {
		return ApiParamsConfig.getParameters();
	}

}
