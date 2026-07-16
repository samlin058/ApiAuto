package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.AssetDisposeJobController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssetDisposeJobControllerListenRo extends AssetDisposeJobController {

    @Param
    public String jobId;
    @Param
    public String jobVersion;

   static {
      DenpendencesProcessor.roDendenpence(AssetDisposeJobControllerListenRo.class);
   }
}


