package Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import pojo.CellData;
import pojo.CheckResult;
import pojo.SQLChecker;

public class SQLCheckUtil {

	/**
	 *   
	 * @param checkerStr 要检查的sql相关字符串
	 * @return   检查后的结果，写回到excel中间去
	 */
	public static String getSQLCheckerResult(String checkerStr) {
		//通过反射转换成一个列表
		List<SQLChecker> cherkerList = JSONObject.parseArray(checkerStr, SQLChecker.class);
		//创建一个结果列表保存每条sql的结果
		List<CheckResult> checkResultList = new ArrayList<CheckResult>();
		for (SQLChecker sqlChecker : cherkerList) {
			String sql = sqlChecker.getSql();//得到了我们要进行验证的sql
			List<Map<String, String>> actualResult = JDBCUtil.executeQuery(sql);
			String actualJson = JSONObject.toJSONString(actualResult);
			/*Map<String, Object> resultMap = records.get(0);
			String actualResult = "";
			Set<String> fields = resultMap.keySet();
			for (String field : fields) {
				actualResult = resultMap.get(field).toString();
			}*/
			//期望的结果
			List<Map<String, String>> expectedResult = sqlChecker.getExpectedResult();
			String expectedJson = JSONObject.toJSONString(expectedResult);
			//创建针对当前这条要检测的sql的一个验证结果的对象
			CheckResult checkResult = null;
			if (expectedJson.equals(actualJson)) {
				checkResult = new CheckResult(sqlChecker.getNo(), actualResult, "success");
			} else {
				checkResult = new CheckResult(sqlChecker.getNo(), actualResult, "failure");
			}
			//每次检测完把这个结果放到列表中去
			checkResultList.add(checkResult);
		}
		return JSONObject.toJSONString(checkResultList);
	}

	/**
	 * 
	 * @param caseId             caseId
	 * @param preValidateSql     sql语句
	 * @param cellNum            写回的列号
	 */
	public static void dataValidata(String caseId, String ValidateSql, int cellNum) {
		// TODO Auto-generated method stub
		if (!StringUtil.isEmpty(ValidateSql)) {
			String ValidataSqlResult = SQLCheckUtil.getSQLCheckerResult(ValidateSql);
			ExcelUtil.resulToWriteList.add(new CellData(caseId, 10, ValidataSqlResult));
		}
	}
	
/*	public static void main(String[] args) {
		String checkerStr = "[{\"no\":\"1\",\"sql\":\"select * from apply where apply_id='123';\",\"expectedResult\":{\"totalNum\":\"0\"}}]";
		List<SQLChecker> cherkerList = JSONObject.parseArray(checkerStr, SQLChecker.class);
		for (SQLChecker sqlChecker : cherkerList) {
			System.out.println(sqlChecker.toString());
		}
	}*/
}
