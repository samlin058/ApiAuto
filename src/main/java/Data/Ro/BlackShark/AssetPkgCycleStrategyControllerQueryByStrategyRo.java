package Data.Ro.BlackShark;

import Data.Apis.BlackShark.AssetPkgCycleStrategyController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssetPkgCycleStrategyControllerQueryByStrategyRo extends AssetPkgCycleStrategyController {

    @Param
    public String strategyCode;
    @Param
    public String strategyVersion;

   static {
      DenpendencesProcessor.roDendenpence(AssetPkgCycleStrategyControllerQueryByStrategyRo.class);
   }
}


