package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.AssetPkgCycleStrategyController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class AssetPkgCycleStrategyControllerSaveAssetPkgCycleStrategyRo extends AssetPkgCycleStrategyController {

    @Body
    public String strategyVersion;
    @Body
    public String assetPkgNo;
    @Body
    public Integer cycleId;
    @Body
    public String strategyCode;

   static {
      DenpendencesProcessor.roDendenpence(AssetPkgCycleStrategyControllerSaveAssetPkgCycleStrategyRo.class);
   }
}


