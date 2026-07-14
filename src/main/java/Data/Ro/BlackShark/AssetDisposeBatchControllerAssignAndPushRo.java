package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.AssetDisposeBatchController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class AssetDisposeBatchControllerAssignAndPushRo extends AssetDisposeBatchController {

    @Body
    public String assignBatchNo;

   static {
      DenpendencesProcessor.roDendenpence(AssetDisposeBatchControllerAssignAndPushRo.class);
   }
}


