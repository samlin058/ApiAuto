package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.MyAssetProjectController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationLibrary.Dependence;
import Service.Annotations.AnnotationLibrary.Dependences;
import Service.Annotations.AnnotationProcessor.*;
import Service.Core.TestContext;
import Service.Util.Universal.JsonHandler;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Dependences({
        @Dependence(project = "blackShark", Class = "AssetProjectController", method = "projectTypeList", keys = "projectTypeCode_MyAssetProjectControllerCreateRo"),
        @Dependence(project = "blackShark", Class = "AssetProjectUserConfigController", method = "userProjectBdList", keys = "bdList_MyAssetProjectControllerCreateRo"),
        @Dependence(project = "blackShark", Class = "AssetProjectUserConfigController", method = "userProjectChargeList", keys = "managerList_MyAssetProjectControllerCreateRo"),
})
@lombok.Data
public class MyAssetProjectControllerCreateRo extends MyAssetProjectController {

    @Body
    public String projectName;
    @Body
    public String projectTypeCode = defaultProjectTypeCode();
    @Body
    public List<?> bdList = defaultBdList();
    @Body
    public List<?> managerList = defaultManagerList();
    @Body
    public List<?> orgList ;


    static {
        DenpendencesProcessor.roDendenpence(MyAssetProjectControllerCreateRo.class);
    }

    private String defaultProjectTypeCode() {
        return JsonHandler.readStringByJsonPath("$.data.[0].value", TestContext.getCacheDataAsString("projectTypeCode_MyAssetProjectControllerCreateRo"));
    }

    /**
     * 默认Bd为鸾刀（luandao）
     * @return
     */
    private List<?> defaultBdList() {
        pm_MyAssetProjectControllerCreateRo bdList_myAssetProjectControllerCreateRo = new pm_MyAssetProjectControllerCreateRo();
        bdList_myAssetProjectControllerCreateRo.setPmCode("luandao");
        bdList_myAssetProjectControllerCreateRo.setPmName("鸾刀");
        List<pm_MyAssetProjectControllerCreateRo> resultList = new ArrayList<>();
        resultList.add(bdList_myAssetProjectControllerCreateRo);
        return resultList;
    }

    private List<?> defaultManagerList() {
        String json = TestContext.getCacheDataAsString("managerList_MyAssetProjectControllerCreateRo");
        String pmCode = (String) JsonHandler.readValueByJsonPath("$.data.[0].value", json);
        String pmName = (String) JsonHandler.readValueByJsonPath("$.data.[0].label", json);
        pm_MyAssetProjectControllerCreateRo ManagerList_myAssetProjectControllerCreateRo = new pm_MyAssetProjectControllerCreateRo();
        ManagerList_myAssetProjectControllerCreateRo.setPmCode(pmCode);
        ManagerList_myAssetProjectControllerCreateRo.setPmName(pmName);
        List<pm_MyAssetProjectControllerCreateRo> resultList = new ArrayList<>();
        resultList.add(ManagerList_myAssetProjectControllerCreateRo);
        return resultList;
    }

    @Data
    public  class pm_MyAssetProjectControllerCreateRo {
        public String pmCode;
        public String pmName;

        public pm_MyAssetProjectControllerCreateRo() {
        }

        public pm_MyAssetProjectControllerCreateRo(String pmCode, String pmName) {
            this.pmCode = pmCode;
            this.pmName = pmName;
        }
    }



}


