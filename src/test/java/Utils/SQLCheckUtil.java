package Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import Utils.ExcelUtil;
import Utils.JDBCUtil;
import Utils.SQLCheckUtil;
import Utils.StringUtil;
import pojo.CellData;
import pojo.CheckResult;
import pojo.SQLChecker;
import pojo.sqlResult;

public class SQLCheckUtil {

	/**
	 *   
	 * @param checkerStr 要检查的sql相关字符串
	 * @return   检查后的结果，写回到excel中间去
	 */
	public static sqlResult getSQLCheckerResult(String checkerStr) {
		boolean comparaResult = true;
		//通过反射转换成一个列表
		List<SQLChecker> cherkerList = JSONObject.parseArray(checkerStr, SQLChecker.class);
		//创建一个结果列表保存每条sql的结果
		List<CheckResult> checkResultList = new ArrayList<CheckResult>();
		for (SQLChecker sqlChecker : cherkerList) {
			String sql = sqlChecker.getSql();
			List<Map<String, String>> actualResult = JDBCUtil.executeQuery(sql,sqlChecker.getDatabase());
			String actualJson = JSONObject.toJSONString(actualResult);
			//期望的结果
			List<Map<String, String>> expectedResult = sqlChecker.getExpectedResult();
			String expectedJson = JSONObject.toJSONString(expectedResult);
			//创建针对当前这条要检测的sql的一个验证结果的对象
			CheckResult checkResult = null;
			if (expectedJson.equals(actualJson)) {
				checkResult = new CheckResult(sqlChecker.getNo(), actualResult, "success");
			} else {
				checkResult = new CheckResult(sqlChecker.getNo(), actualResult, "failure");
				comparaResult =false;
			}
			//每次检测完把这个结果放到列表中去
			checkResultList.add(checkResult);
		}
		sqlResult sqlResult = new sqlResult();
		sqlResult.setCompareResult(comparaResult);
		sqlResult.setResultStr(JSONObject.toJSONString(checkResultList));
		return sqlResult;
	}

	/**
	 * 
	 * @param caseId             caseId
	 * @param preValidateSql     sql语句
	 * @param cellNum            写回的列号
	 */
	public static boolean dataValidata(String caseId, String ValidateSql, int cellNum) {
		boolean result = true;
		if (!StringUtil.isEmpty(ValidateSql)) {
			sqlResult sqlResult= SQLCheckUtil.getSQLCheckerResult(ValidateSql);
			String ValidataSqlResult =sqlResult.getResultStr();
			result =sqlResult.isCompareResult();
			ExcelUtil.resulToWriteList.add(new CellData(caseId, cellNum, ValidataSqlResult));
		}
		return result;
	}
	
	public static void main(String[] args) {
		String checkerStr = "[{\"no\":\"1\",\"sql\":\"select * from apply where apply_id='123';\",\"expectedResult\":{\"totalNum\":\"0\"}},{\"no\":\"2\",\"sql\":\"select * from apply where apply_id='1234';\",\"expectedResult\":{\"totalNum\":\"1\"}}]";
		List<SQLChecker> cherkerList = JSONObject.parseArray(checkerStr, SQLChecker.class);
		for (SQLChecker sqlChecker : cherkerList) {
			System.out.println(sqlChecker.getNo());
			System.out.println(sqlChecker.getSql());
			System.out.println(sqlChecker.getExpectedResult());
		}
	}
}
