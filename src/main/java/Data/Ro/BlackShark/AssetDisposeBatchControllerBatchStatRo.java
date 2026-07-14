package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.AssetDisposeBatchController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssetDisposeBatchControllerBatchStatRo extends AssetDisposeBatchController {

    @Param
    public String disposerId;

   static {
      DenpendencesProcessor.roDendenpence(AssetDisposeBatchControllerBatchStatRo.class);
   }
}


