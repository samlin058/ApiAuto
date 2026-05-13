package Data.Ro.BlackShark;

import Application.DataFactory.BlackShark.ProjectManagement;
import Data.Apis.BlackShark.PreAssetController;
import Data.Apis.BlackShark.PreAssetPkgBatchController;
import Data.Apis.BlackShark.PreAssetPkgController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationLibrary.Dependence;
import Service.Annotations.AnnotationLibrary.Dependences;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Config.Config;
import Service.Util.Universal.JsonHandler;
import Service.Util.Universal.StringHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

@Dependences({
        @Dependence(project = "blackShark", Class = "PreAssetController", method = "repayPlanFeeTypeList", keys = "calculateCost_PreAssetControllerImportAssetRo")
})
@lombok.Data
public class PreAssetControllerImportAssetRo extends PreAssetController {

    @Body
    public String overdueBeginDay;
    @Body
    public String assetPkgNo;
    @Body
    public String pkgBaseId;
    @Body
    public Integer validYearRate = 36; //默认合规利率为年化36%
    @Body
    public List<?> sheetCodes = defaultSheetCodes(); //默认excel中所有案件信息都导入
    @Body
    public List<?> attachList;
    @Body
    public String templateId;
    @Body
    public String assetTypeLabel;
    @Body
    public String assetType;
    @Body
    public String calculateCost = defaultCalculateCost();
    @Body
    public String projectCode;
    @Body
    public String templateName;
    @Body
    public String periodInterval = "FIXED_INTERVAL";//计算逻辑，默认：按还款计划（ps：这块是前端代码写死，不是后端接口给的枚举）
    @Body
    public Boolean calOverdueFee = false;
    @Body
    public String assetName;
    @Body
    public Boolean genRepayPlan = false;
    @Body
    public String overdueYearRate;

    static {
        DenpendencesProcessor.roDendenpence(PreAssetControllerImportAssetRo.class);
    }

    /**
     * 资产包导入数据
     *
     * @param assetPkgNo 资产包编号
     */
    public PreAssetControllerImportAssetRo(String assetPkgNo) {
        this.assetPkgNo = assetPkgNo;
        PreAssetPkgBatchController preAssetPkgBatchController = new PreAssetPkgBatchController();
        String pkgInfo = preAssetPkgBatchController.projectPkgList();
        if (JsonHandler.existsInHttpResult(pkgInfo, "children", "assetPkgNo", this.assetPkgNo)) {
            extracted(pkgInfo);
        } else {
            // TODO 如果luandao用户没有该项目，则给鸾刀加上项目权限
            ProjectManagement projectManagement = new ProjectManagement();
            projectManagement.addProjectPermissionByPkgNo(assetPkgNo, "luandao");
            String pkgInfoAgain = preAssetPkgBatchController.projectPkgList();
            extracted(pkgInfoAgain);
        }
        setTemplateId(assetPkgNo);
        // 根据资产包code设置资产包相关信息
        String periodIntervalJsonStr = preAssetPkgBatchController.queryLastRuleConf(new PreAssetPkgBatchControllerQueryLastRuleConfRo(this.pkgBaseId));
        String periodInterval = JsonHandler.readStringByJsonPath("$.data.periodInterval", periodIntervalJsonStr);
        if (StringHandler.isEmpty(periodInterval)) {
            this.periodInterval = "FIXED_INTERVAL";
        } else {
            this.periodInterval =  periodInterval;
        }
    }

    private void setTemplateId(String assetPkgNo) {
        PreAssetPkgController preAssetPkgController = new PreAssetPkgController();
        String templateIdJson = preAssetPkgController.queryBaseData(new PreAssetPkgControllerQueryBaseDataRo(assetPkgNo));
        String templateListJson = JsonHandler.readStringByJsonPath("$.data.templates", templateIdJson);
        List<Object> templateIdJsonList = JsonHandler.filterByFieldValue(templateListJson, "templateName", "信用贷默认模板");
        // 拿第一个json对象的id
        this.templateId =  JsonHandler.readStringByJsonPath("$.id", templateIdJsonList.get(0).toString());
    }

    private void extracted(String pkgInfo) {
        String pkgJson = null;
        JSONArray jsonArray = new JSONArray(JsonHandler.readStringByJsonPath("$.data", pkgInfo));
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JSONArray children = jsonObject.getJSONArray("children");
            for (int j = 0; j < children.length(); j++) {
                JSONObject child = children.getJSONObject(j);
                // 判断child是否包含assetPkgNo字段，并与keyStr进行匹配
                if (child.has("assetPkgNo") && child.getString("assetPkgNo").equals(this.assetPkgNo)) {
                    pkgJson = child.toString();
                }
            }
        }
        this.pkgBaseId = JsonHandler.readStringByJsonPath("$.id", pkgJson);
        this.assetName = JsonHandler.readStringByJsonPath("$.assetName", pkgJson);
        this.projectCode = JsonHandler.readStringByJsonPath("$.projectCode", pkgJson);
        this.assetType = JsonHandler.readStringByJsonPath("$.assetTypeValue", pkgJson);
        this.assetTypeLabel = JsonHandler.readStringByJsonPath("$.assetTypeLabel", pkgJson);
    }

    private String defaultCalculateCost() {
        String result = Config.getCacheData("calculateCost_PreAssetControllerImportAssetRo").toString();
        String PRINCIPAL = JsonHandler.readStringByJsonPath("$.data[0].value", result);
        String INTEREST = JsonHandler.readStringByJsonPath("$.data[1].value", result);
        return PRINCIPAL + "," + INTEREST;
    }

    private List<?> defaultSheetCodes() {
        return this.sheetCodes = Arrays.asList("LoanInfo", "BaseInfo", "ContractInfo", "EmergencyContactInfo", "FundFlowInfo", "RepayRecord", "RepayPlan");
    }

    public static void main(String[] args) {
        PreAssetControllerImportAssetRo preAssetControllerImportAssetRo = new PreAssetControllerImportAssetRo("20241227_003");
        StringHandler.jsonPrint("aa", preAssetControllerImportAssetRo);
    }


}


