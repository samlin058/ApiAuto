package Data.Ro.BlackShark;

import Data.Apis.BlackShark.AssetPkgCycleStrategyController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssetPkgCycleStrategyControllerQueryStrategyByPkgNoAndCycleIdRo extends AssetPkgCycleStrategyController {

    @Param
    public String cycleId;
    @Param
    public String assetPkgNo;

   static {
      DenpendencesProcessor.roDendenpence(AssetPkgCycleStrategyControllerQueryStrategyByPkgNoAndCycleIdRo.class);
   }
}


