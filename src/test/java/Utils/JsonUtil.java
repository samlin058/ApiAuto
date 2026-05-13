package Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.JsonPath;

import Utils.StringUtil;

public class JsonUtil {

	/**
	  * 获取json中指定key的值
	  * @param key  
	  * @param json
	  * @return String 值,如果key不存在,则返回空字符串
	  */
	public static String getKeyVal(String key, String json) {
		String resultStr = "";
		JSONObject jsonObject = JSONObject.parseObject(json);
		if (jsonObject.containsKey(key)) {
			resultStr = jsonObject.getString(key);
		} else {
			resultStr = getJsonVal(key, jsonObject);
		}
		return resultStr;
	}

	/**
	 * 递归获取json中指定key的值
	 * @param key
	 * @param jsonObject
	 * @return
	 */
	private static String getJsonVal(String key, JSONObject jsonObject) {
		// 定义一个字符串变量
		String resultStr = "";
		// 把jsonObject转换成一个map对象
		Map<String, Object> map = jsonObject.getInnerMap();
		// 获取map的键集合
		Set<String> mapKey = map.keySet();
		// 遍历Set
		for (String keyStr : mapKey) {
			// 获取map中的值
			String value = map.get(keyStr).toString();
			// 值中包含{代表是对象，值中包含[代表是数组
			if (keyStr.equals(key)) {
				resultStr = value;
				break;
			} else if (value.substring(0, 1).equals("[")) {
				JSONArray jsonArray = JSONObject.parseArray(value);
				for (Object object : jsonArray) {
					JSONObject innerObject = JSONObject.parseObject(object.toString());
					if (innerObject.containsKey(key)) {
						resultStr = innerObject.getString(key);
					}

				}
			} else if (value.substring(0, 1).equals("{")) {
				JSONObject innerObject = JSONObject.parseObject(value);
				if (innerObject.containsKey(key)) {
					resultStr = innerObject.getString(key);
				} else {
					resultStr = getJsonVal(key, innerObject);
				}
			}
		}
		return resultStr;
	}


	public static void jsonUtil(String key, String json) {
		JSONObject jsonObject = JSON.parseObject(json);
		if (jsonObject.containsKey(key)) {
			System.out.println(jsonObject.getString(key));
		} else {
			// isInnerJson(key, json);
		}
	}

	private static List<String> getOneKeyAllValue(String key, String json) {
		// 定义一个数组存储key对应值
		List<String> keyValueList = new ArrayList<String>();
		// 把json字符串转换为JSONObject对象
		JSONObject jsonObject = JSON.parseObject(json);
		// 转为map对象形式
		HashMap<String, Object> map = (HashMap<String, Object>) jsonObject.getInnerMap();
		// 获取map中key集合
		Set<String> mapKey = map.keySet();
		// 遍历key
		for (String keyString : mapKey) {
			if (keyString.equals(key)) {
				keyValueList.add((String) map.get(keyString));
			}
			// 处理内部map对象的key和value
			String value = map.get(keyString).toString();
			// System.out.println(value);
			// 如果值是json数组，继续遍历
			if (value.substring(0).equals("[")) {
				System.out.println(value);
				JSONArray jsonArray = JSON.parseArray(value);
				for (Object object : jsonArray) {
					System.out.println(object.toString());
					JSONObject InnerjsonObject = JSON.parseObject(object.toString());
					// 转为map对象形式
					HashMap<String, Object> InnerMap = (HashMap<String, Object>) InnerjsonObject.getInnerMap();
					// 获取map中key集合
					Set<String> InnerMapKey = map.keySet();
					// 遍历key
					for (String InnerKey : InnerMapKey) {
						if (InnerKey.equals(key)) {
							keyValueList.add((String) map.get(InnerKey));
						}
					}
				}
			} else if (value.substring(0).equals("{")) { // 如果值是一个json字符串
				System.out.println(value);
				JSONObject InnerjsonObject = JSON.parseObject(value);
				if (InnerjsonObject.containsKey(key)) {
					System.out.println(InnerjsonObject.getString(key));
				} else {
					getOneKeyAllValue(key, value);
				}
			}
		}
		return keyValueList;
	}

	private static List<String> getOneKeyAllValue2(String key, String json) {
		// 定义一个数组存储key对应值
		List<String> keyValueList = new ArrayList<String>();
		// 把json字符串转换为JSONObject对象
		JSONObject jsonObject = JSON.parseObject(json);
		// 转为map对象形式
		HashMap<String, Object> map = (HashMap<String, Object>) jsonObject.getInnerMap();
		if (jsonObject.containsKey(key)) {
			keyValueList.add((String) map.get(key));
		}
		// 获取map中key集合
		Set<String> mapKey = map.keySet();
		// 遍历key
		for (String keyString : mapKey) {
			// 处理内部map对象的key和value
			String value = map.get(keyString).toString();
			// System.out.println(value);
			// 如果值是json数组，继续遍历
			if (value.substring(0, 1).equals("[")) {
				System.out.println(value);
				JSONArray jsonArray = JSON.parseArray(value);
				for (Object object : jsonArray) {
					System.out.println(object.toString());
					JSONObject InnerjsonObject = JSON.parseObject(object.toString());
					if (InnerjsonObject.containsKey(key)) {
						keyValueList.add((String) map.get(key));
					} else {
						// TODO 递归存在问题，需要优化
						getOneKeyAllValue(key, value);
					}
				}
			} else if (value.substring(0, 1).equals("{")) { // 如果值是一个json字符串
				System.out.println(value);
				JSONObject InnerjsonObject = JSON.parseObject(value);
				if (InnerjsonObject.containsKey(key)) {
					System.out.println(InnerjsonObject.getString(key));
					keyValueList.add((String) map.get(key));
				} else {
					// TODO 递归存在问题，需要优化
					getOneKeyAllValue(key, value);
				}
			}
		}
		return keyValueList;
	}

	public static Object getValueByJsonpath(String rule, String json) {
		Object value = JsonPath.read(json, rule);
		return value;
	}

	/**
	 * json字符串转map
	 * @param json
	 * @return HashMap<String,String>
	 *
	 * @author       luandao
	 * @Date         2022年5月24日
	 *
	 */
	public static HashMap<String, Object> jsonStrToMap(String json) {
		HashMap<String, Object> result = new HashMap<>();
		if (!StringUtil.isEmpty(json)) {
			result = JSONObject.parseObject(json, HashMap.class);
		}
		return result;
	}

	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		String rule = "$.data.student.name";
		String json = "{\"code\":0,\"message\":\"成功\",\"data\":{\"student\":{\"name\":\"xiaoming\",\"age\":3,\"sex\":\"man\"}}}";
		String value = (String) getValueByJsonpath(rule, json);
		System.out.println(value);

	}

}
