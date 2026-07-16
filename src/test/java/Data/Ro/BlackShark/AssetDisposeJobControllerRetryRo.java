package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.AssetDisposeJobController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssetDisposeJobControllerRetryRo extends AssetDisposeJobController {

    @Param
    public Integer id;

   static {
      DenpendencesProcessor.roDendenpence(AssetDisposeJobControllerRetryRo.class);
   }
}


