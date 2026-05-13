package Data.Ro.BlackShark;

import Data.Apis.BlackShark.AssetDisposeRevokeController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssetDisposeRevokeControllerAgainRevokeRo extends AssetDisposeRevokeController {

    @Param
    public String assignBatchNo;
    @Param
    public String operationId;

   static {
      DenpendencesProcessor.roDendenpence(AssetDisposeRevokeControllerAgainRevokeRo.class);
   }
}


