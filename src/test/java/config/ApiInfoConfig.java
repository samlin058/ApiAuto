package config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Util.ExcelUtil;
import Util.PropertiesUtil;
import pojo.ApiInfo;

public class ApiInfoConfig {

	private static List<ApiInfo> apiInfoList;

	private static Map<String, ApiInfo> apiInfoMap;
    
	//初始化数据
	static {
		if (apiInfoList == null) {
			apiInfoList = new ArrayList<ApiInfo>();
		}
		
		if (apiInfoMap == null) {
			apiInfoMap = new HashMap<>();
		}
		
		String excelPath = getPath();
		int sheetNum = getSheetNum();
		
		//加载Api数据
		ExcelUtil.read(excelPath, sheetNum, ApiInfo.class);
		getApiInfoMap();
	}

	/**
	 * 将apiInfoList转化成map数据格式，键为apiId，值为apiInfo对象
	 */
	private static void getApiInfoMap() {
		for (ApiInfo apiInfo : apiInfoList) {
			String apiId = apiInfo.getApiId();
			apiInfoMap.put(apiId, apiInfo);
		}
	}

	/**
	 * 通过apiId获取type	
	 * @param apiId
	 * @return
	 */
	public static String getTpyeByApiId(String apiId) {
		return apiInfoMap.get(apiId).getType();
	}

	/**
	 * 通过apiId获取url
	 * @param apiId
	 * @return
	 */
	public static String getUrlByApiId(String apiId) {
		return apiInfoMap.get(apiId).getUrl();
	}

	/**
	 * 获得apiInfoList数据
	 * @return
	 */
	public static List<ApiInfo> getApiInfoList() {
		return apiInfoList;
	}

	/**
	 * 往apiInfoList添加ApiInfo对象
	 * @param apiInfo
	 */
	public static void add(ApiInfo apiInfo) {
		apiInfoList.add(apiInfo);
	}
	
	/**
	 *  从配置中获得读取excel路径
	 * @return
	 */
	private static String getPath() {
		String path = (String) PropertiesUtil.properties.get("ApiInfoConfig.Path");
		return path;
	}
    
	/**
	 * 从配置中获得读取excel的sheet位置
	 * @return
	 */
	private static int getSheetNum() {
		Object num = PropertiesUtil.properties.get("ApiInfoConfig.SheetNum");
		int number = Integer.parseInt(num.toString());
		return number;
	}
}
