package Data.Ro.BlackShark;

import Data.Apis.BlackShark.AssetDisposeJobController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssetDisposeJobControllerQueryResultRo extends AssetDisposeJobController {

    @Param
    public Integer jobId;

   static {
      DenpendencesProcessor.roDendenpence(AssetDisposeJobControllerQueryResultRo.class);
   }
}


