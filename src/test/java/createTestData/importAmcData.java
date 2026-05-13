package createTestData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Utils.HttpUtil2;
import Utils.JsonUtil;


public class importAmcData {
	// 上传文件地址
	private static String uploadFileUrl = "http://black-shark.x-amc.wke-office.test.wacai.info/blackShark/preAssetPkgBatch/uploadFile";
	// 本地文件路径
	private static String localFileUrl = "src/test/resources/P2P资产模板（版本8）.xlsx";
	// 获取资产导入规则配置
	private static String importRuleUrl = "http://black-shark.x-amc.wke-office.test.wacai.info/blackShark/preAssetPkgBatch/queryLastRuleConf";
	// 导入确认
	private static String importUrl = "http://black-shark.x-amc.wke-office.test.wacai.info/blackShark/preAsset/import";
	
	private static String assetPkgNo;
	private static String validYearRate;
	private static String genRepayPlan;
	private static String calOverdueFee;
	private static String repayCalcCode;
	private static String periodInterval;
	private static String intervalNumber;
	private static String calculateCost;
	private static ArrayList<Object> attachList=new ArrayList<>();
	private static String attachName;
	private static String attachId;

	public static void main(String[] args) { 
		importFile();
	}

	private static void importFile() {
		HashMap<String, String> uploadHeaders = new HashMap<String, String>();
		uploadHeaders.put("X-Login-User", "luandao");
		File file = new File(localFileUrl);
		String result = null;
		try {
			result = HttpUtil2.postByFile(uploadFileUrl, file, uploadHeaders);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String dataStr = JsonUtil.getKeyVal("Data", result);
		attachName = JsonUtil.getKeyVal("fileName", dataStr);
		attachId = JsonUtil.getKeyVal("fileId", dataStr);
        String pkgid = "1633448777224990720";
        HashMap<String, Object> paramsMap = new HashMap<String, Object>();
        Map<String, Object> importRuleHeader = new HashMap<String, Object>();
        paramsMap.put("pkgBaseId", pkgid);
        String importRuleResutl =HttpUtil2.get(importRuleUrl, paramsMap, importRuleHeader);
        String importRuleUrlData = JsonUtil.getKeyVal("Data", importRuleResutl);
        validYearRate = JsonUtil.getKeyVal("validYearRate", importRuleUrlData);
        genRepayPlan = JsonUtil.getKeyVal("genRepayPlan", importRuleUrlData);
        repayCalcCode = JsonUtil.getKeyVal("repayCalcCode", importRuleUrlData);
        calOverdueFee = JsonUtil.getKeyVal("calOverdueFee", importRuleUrlData);
        periodInterval = JsonUtil.getKeyVal("periodInterval", importRuleUrlData);
        intervalNumber = JsonUtil.getKeyVal("intervalNumber", importRuleUrlData);
        calculateCost = JsonUtil.getKeyVal("calculateCost", importRuleUrlData);
        assetPkgNo = "20220505_001";
        Map<String, Object> importHeader = new HashMap<String, Object>();
        importHeader.put("X-Login-User", "luandao");
        HashMap<String, Object> importParams = new HashMap<String, Object>();
        importParams.put("assetPkgNo", assetPkgNo);
        importParams.put("validYearRate", validYearRate);
        importParams.put("genRepayPlan", genRepayPlan);
        importParams.put("repayCalcCode", repayCalcCode);
        importParams.put("calOverdueFee", calOverdueFee);
        importParams.put("periodInterval", periodInterval);
        importParams.put("intervalNumber", intervalNumber);
        importParams.put("calculateCost", calculateCost);
        Map<String, String> attachMap = new HashMap<>();
        attachMap.put("attachName", attachName);
        attachMap.put("attachId", attachId);
        attachList.add(attachMap);
        importParams.put("attachList", attachList);
        String fresult = HttpUtil2.postByJson(importUrl, importParams, importHeader);
	}
}
