package Data.Ro.BlackShark;

import Data.Apis.BlackShark.PreAssetPkgController;
import Service.Annotations.AnnotationLibrary.Dependence;
import Service.Annotations.AnnotationLibrary.Dependences;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.*;
import Service.Config.Config;
import Service.Util.Universal.DateHandler;
import Service.Util.variable.GenerationHandler;
import Service.Util.Universal.JsonHandler;

@Dependences({
        @Dependence(project = "blackShark", Class = "BizDictController", method = "query", param = "{\"dictType\":\"ASSET_TYPE\"}", keys = "assetTypeCode_PreAssetPkgControllerAssetPkgBaseCreateRo"),
        @Dependence(project = "blackShark", Class = "AssetProjectController", method = "list", keys = "projectCode_PreAssetPkgControllerAssetPkgBaseCreateRo")
})
@lombok.Data
public class PreAssetPkgControllerAssetPkgBaseCreateRo extends PreAssetPkgController {

    @Body
    public Integer prcpBalance;
    @Body
    public String projectCode = defuatProjectCode();
    @Body
    public String pkgDate = DateHandler.getTodayY_M_DString();
    @Body
    public String assetName = GenerationHandler.getAssetPkgName();
    @Body
    public String assetTypeCode = defuatAssetTypeCode();

    static {
        DenpendencesProcessor.roDendenpence(PreAssetPkgControllerAssetPkgBaseCreateRo.class);
    }

    private String defuatProjectCode() {
        return (String) JsonHandler.readValueByJsonPath("$.data[0].value", Config.getCacheDataAsString("projectCode_PreAssetPkgControllerAssetPkgBaseCreateRo"));
    }

    private String defuatAssetTypeCode() {
        String json = Config.getCacheDataAsString("assetTypeCode_PreAssetPkgControllerAssetPkgBaseCreateRo");
        return (String) JsonHandler.readValueByJsonPath("$.data[0].value", json);
    }
}


