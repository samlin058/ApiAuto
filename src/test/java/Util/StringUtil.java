package Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringUtil {
	/**
	 * 判断字符串是否为空
	 * 1、等于null
	 * 2、"   "等于空
	 * 3、""等于空
	 * @param str  字符串
	 * @return
	 */
	public static boolean isEmpty(String str) {
		Boolean result = (str == null) || (str.replace("     ", "")).length() == 0;
		return result;
	}

	/**
	 *    分解期望jsonKey的值
	 * @param keyStr 
	 * @return
	 */
	public static List<Map<String, String>> analysisHopeStr(String keyStr) {
		List<Map<String, String>> resultList= new ArrayList<>();
		//创建存放结果对象
		HashMap<String, String> resultMap = new HashMap<String, String>();
		//创建存放key的list对象
		List<String> keyList = new ArrayList<String>();
		//创建存放value的list对象
		List<String> valueList = new ArrayList<String>();
		// 分解key和value
		String[] oneList = keyStr.split(",");
		for (int i = 0; i < oneList.length; i++) {
			if (i % 2 == 0) {
				keyList.add(oneList[i].toString());
			} else {
				valueList.add(oneList[i].toString());
			}
		}
		//将分解的值存入map对象
		int forNum = oneList.length / 2;
		for (int j = 0; j < forNum; j++) {
			String key = keyList.get(j);
			String value = valueList.get(j);
			resultMap.put(key, value);
			resultList.add(resultMap);    		
		}
		return resultList;
	}
	
    /*
     * 演示：将特殊的字符串解析为map数组	
     */
	public static void main(String[] args) {
		String test = "data,0,banner,0";
		List<Map<String, String>> aHashMap = analysisHopeStr(test);
		int a=aHashMap.size();
		System.out.println(a);
	}
}
