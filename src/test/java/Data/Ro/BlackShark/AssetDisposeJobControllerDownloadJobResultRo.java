package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.AssetDisposeJobController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssetDisposeJobControllerDownloadJobResultRo extends AssetDisposeJobController {

    @Param
    public String jobId;

   static {
      DenpendencesProcessor.roDendenpence(AssetDisposeJobControllerDownloadJobResultRo.class);
   }
}


