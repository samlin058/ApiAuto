package Util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;

public class HeadUtil {
	public static HashMap<String, String> handle(String isNeedHead, String headData) {
		Map<String, String> result = new HashMap<>();
		Map<String, String> headMap = new HashMap<>();
		String url = "http://paydayloan.samlin.dsqtest.kuainiujinke.com/v3/user/login";
		if ("yes".equalsIgnoreCase(isNeedHead)) {
			if (!StringUtil.isEmpty(headData)) {
				headMap = (Map<String, String>) JSONObject.parse(headData);
				Set<String> headKey = headMap.keySet();
				for (String key : headKey) {
					String field = headMap.get(key);
					if (field.contains("$")) {
						String regex = "\\$\\{(.*?)\\}";
						Pattern pattern = Pattern.compile(regex);
						Matcher matcher = pattern.matcher(field);
						String telephone = null;
						while (matcher.find()) {
							telephone = matcher.group(1);
						}
						Map<String, String> paramsMap = new HashMap<>();
						paramsMap.put("phone", telephone);
						paramsMap.put("password", "123456");
						String loginResult = HttpUtil.postByParams(url, paramsMap);
						String tokenStr = JsonUtil.getKeyVal("token", loginResult);
						result.put(key, tokenStr);
					} else {
						result.put(key, headMap.get(field));
					}
				}
			}
		} else if ("no".equalsIgnoreCase(isNeedHead) || StringUtil.isEmpty(isNeedHead) || isNeedHead.length() > 0) {
			System.out.println("不需要请求头");
		}
		Map<String, String> resultStr = result;
		return (HashMap<String, String>) resultStr;
	}

	public static void main(String[] args) {
		String aString = "{\"X-TOKEN\":\"${13916810070}\"}";
		String bString = "yes";
		handle(bString, aString);
	}
}
