package config;

import java.util.ArrayList;
import java.util.List;

import Utils.ExcelUtil;
import Utils.PropertiesUtil;
import pojo.ApiParams;

public class ApiParamsConfig {

	private static List<ApiParams> apiParamsList;

	static {
		if (apiParamsList == null) {
			apiParamsList = new ArrayList<ApiParams>();
		}

		String path = getPath();
		int sheetNum = getSheetNum();
		//加载数据
		ExcelUtil.read(path, sheetNum, ApiParams.class);
	}

	public static void add(ApiParams apiParams) {
		apiParamsList.add(apiParams);
	}

	public static List<ApiParams> getApiparamsList() {
		return apiParamsList;
	}

	/**
	 *  获取api请求参数数据
	 * @return  Object[][]
	 */
	public static Object[][] getParameters() {
		int size = apiParamsList.size();
		Object[][] datas = new Object[size][7];
		for (int i = 0; i < size; i++) {
			ApiParams apiParams = apiParamsList.get(i);
			datas[i][0] = apiParams.getCaseId();
			datas[i][1] = apiParams.getApiId();
			datas[i][2] = apiParams.getRequestData();
			datas[i][3] = apiParams.getHeadData();
			datas[i][4] = apiParams.getExpectedResponseData();
			datas[i][5] = apiParams.getPreValidataSql();
			datas[i][6] = apiParams.getAfterValidataSql();
		}
		return datas;
	}
    
	/**
	 *  从配置中获得读取excel路径
	 * @return
	 */
	private static String getPath() {
		String path = (String) PropertiesUtil.pathProperties.get("ApiParamsConfig.Path");
		return path;
	}
    
	/**
	 * 从配置中获得读取excel的sheet位置
	 * @return
	 */
	private static int getSheetNum() {
		Object num = PropertiesUtil.pathProperties.get("ApiParamsConfig.SheetNum");
		int number = Integer.parseInt(num.toString());
		return number;
	}

}
