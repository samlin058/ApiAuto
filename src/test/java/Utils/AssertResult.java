package Utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;


public class AssertResult {

	/**
	 * 判断期望结果与实际结果的对比值
	 * @param realResult
	 * @param expectedResult
	 * @return boolean
	 *
	 * @author       luandao
	 * @Date         2022年5月28日 
	 *
	 */
	public static boolean isPass(String realResult, String expectedResult, boolean preSqlcheckResult,
			boolean afterSqlcheckResult) {
		boolean compareResult = false;
		if (StringUtil.isEmpty(expectedResult)) {
			return compareResult = true;
		} else {
			HashMap<String, Object> expectMap = JsonUtil.jsonStrToMap(expectedResult);
			Set<String> keySet = expectMap.keySet();
			for (String rule : keySet) {
				Object actualValue = JsonUtil.getValueByJsonpath(rule, realResult);
				Object expectValue = expectMap.get(rule);
				if (actualValue instanceof String) {
					expectValue = (String) expectValue;
				} else if (actualValue instanceof Integer) {
					if (expectValue instanceof BigDecimal) {
						// 期望数值的类型是java.math.BigDecimal，需要转一层
						BigDecimal expectValueMid = (BigDecimal) expectValue;
						expectValue = expectValueMid.intValue();
					} else {
						expectValue = (Integer) expectValue;
					}
				} else if (actualValue instanceof Double) {
					if (expectValue instanceof BigDecimal) {
						// 期望数值的类型是java.math.BigDecimal，需要转一层
						BigDecimal expectValueMid = (BigDecimal) expectValue;
						expectValue = expectValueMid.doubleValue();
					} else {
						expectValue = (Double) expectValue;
					}
				}
				compareResult = actualValue.equals(expectValue);
				if (!compareResult) {
					return false;
				}
			}
		}
		boolean result = compareResult && preSqlcheckResult && afterSqlcheckResult;
		return result;
	}

}
