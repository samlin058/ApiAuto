package Data.Ro.BlackShark;

import Data.Apis.BlackShark.AssetDisposeJobController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class AssetDisposeJobControllerQuerySubStrategyExecuteResultRo extends AssetDisposeJobController {

    @Body
    public String strategyVersion;
    @Body
    public String strategyCode;

   static {
      DenpendencesProcessor.roDendenpence(AssetDisposeJobControllerQuerySubStrategyExecuteResultRo.class);
   }
}


