package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.AssetPkgCycleController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Util.Universal.JsonHandler;
import Service.Util.Universal.StringHandler;


@lombok.Data
public class AssetPkgCycleControllerSaveRo extends AssetPkgCycleController {

    @Body
    public String id;
    @Body
    public String assetPkgId;
    @Body
    public String startDate;
    @Body
    public String endDate;
    @Body
    public String point;

    static {
        DenpendencesProcessor.roDendenpence(AssetPkgCycleControllerSaveRo.class);
    }

    public AssetPkgCycleControllerSaveRo(String assetPkgId) {
        AssetPkgCycleController assetPkgCycleController = new AssetPkgCycleController();
        String requestResult = assetPkgCycleController.listByPkgIds(new AssetPkgCycleControllerListByPkgIdsRo(assetPkgId));
        this.id = JsonHandler.readStringByJsonPath("$.data.[1].id", requestResult);
        this.assetPkgId = assetPkgId;
    }

    public static void main(String[] args) {
        AssetPkgCycleControllerSaveRo assetPkgCycleControllerSaveRo = new AssetPkgCycleControllerSaveRo("1948463313489555456");
        StringHandler.jsonPrint("aaa", assetPkgCycleControllerSaveRo);
    }

}


