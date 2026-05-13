package Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;


import config.ApiInfoConfig;

public class DataUtil {
	
    /**
     * url上参数赋值
     * @param apiId     获取url的id
     * @param urlParam  替换参数值
     * @return String   url
     *
     * @author       luandao
     * @Date         2020年12月3日 
     *
     */
	public static String urlGetData(String apiId, String urlParam) {
		// 1、获取url
		String url = ApiInfoConfig.getUrlByApiId(apiId);
		// 2、判断url是否带参数
		if (!urlParam.isEmpty()) {
			HashMap<String, String> urlParams = (HashMap<String, String>) JSONObject.parse(urlParam);
			String regex = "\\{(.*?)\\}";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(url);
			String regexResult = null;
			while (matcher.find()) {
				regexResult = matcher.group(0);
				String result = "";
				Set<String> keyfield = urlParams.keySet();
				for (String key : keyfield) {
					if (regexResult.equals(key)) {
						result = url.replace(key, urlParams.get(key));
					}
				}
				url = result;
			}
		}
		// 3、拼接url
		String urlValue = PropertiesUtil.pathProperties.getProperty("url") + url;
		return urlValue;
	}
    
	/**
	 * 获得head数据
	 * @param headData
	 * @return HashMap<String,String>  
	 *
	 * @author       luandao
	 * @Date         2020年12月3日 
	 *
	 */
	public static HashMap<String, String> getHeadData(String headData) {
		HashMap<String, String> result = new HashMap<>();
		if (StringUtil.isEmpty(headData)) {
			Map<String, String> headRequest = (Map<String, String>) JSONObject.parse(headData);
			result = (HashMap<String, String>) headRequest;
		}
		return result;
	}
    
	/**
	 * 获得请求参数
	 * @param requestdata
	 * @return HashMap<String,String>
	 *
	 * @author       luandao
	 * @Date         2020年12月3日 
	 *
	 */
	public static HashMap<String, String> getRequestParams(String requestdata) {
		HashMap<String, String> result = new HashMap<>();
		if (StringUtil.isEmpty(requestdata)) {
			Map<String, String> requestDataMap = (Map<String, String>) JSONObject.parse(requestdata);
			result = (HashMap<String, String>) requestDataMap;
		}
		return result;
	}
    
	/**
	 *  提取json格式中指定key的值
	 * @param regex
	 * @param json
	 * @return Object
	 *
	 * @author       luandao
	 * @Date         2020年12月3日 
	 *
	 */
	/*public static Object jsonPath(String regex, String json) {
		Object value = JSONPath.read(json, regex);
		return value;
	}	*/		
    
	/**
	 * 实例
	 * @param args void
	 *
	 * @author       luandao
	 * @Date         2020年12月3日 
	 *
	 */
	/*public static void main(String[] args) {
		String json = "{'text':'张三','expensive':6,'body':{'rvNoNum':23,'rvNoRecords':[{'score':4,'rvAddress':'2','consignments':null},{'score':8,'rvAddress':'3','consignments':null}]}}";
		String regex = "$.*";
		Object result = jsonPath(regex, json);
		System.out.println(result);
	}*/
	
	
}
