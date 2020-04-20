package Util;

import java.util.HashMap;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

public class AssertResult {

	public static boolean isPass(String realResult, String expectedResult) {
		boolean compareResult = false;
		if (StringUtil.isEmpty(expectedResult)) {
			return compareResult = true;
		}
		JSONObject jsonObject = JSONObject.parseObject(expectedResult);
		HashMap<String, Object> map = (HashMap<String, Object>) jsonObject.getInnerMap();
		Set<String> Sets = map.keySet();
		for (String key : Sets) {
			Object Val = map.get(key);
			String realVal = Val + "";
			String resulStr = JsonUtil.getKeyVal(key, realResult);
			if (realVal.equals(resulStr)) {
				compareResult = true;
			} else if (realVal == "null" && resulStr == "null") {
				compareResult = true;
			} else {
				compareResult = false;
			}
		}
		return compareResult;
	}
}
