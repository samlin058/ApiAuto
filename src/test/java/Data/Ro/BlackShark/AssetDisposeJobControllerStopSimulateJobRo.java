package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.AssetDisposeJobController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class AssetDisposeJobControllerStopSimulateJobRo extends AssetDisposeJobController {

    @Body
    public Integer jobId;

   static {
      DenpendencesProcessor.roDendenpence(AssetDisposeJobControllerStopSimulateJobRo.class);
   }
}


