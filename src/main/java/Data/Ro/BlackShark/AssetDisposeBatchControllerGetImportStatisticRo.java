package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.AssetDisposeBatchController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssetDisposeBatchControllerGetImportStatisticRo extends AssetDisposeBatchController {

    @Param
    public String asyncRecordId;

   static {
      DenpendencesProcessor.roDendenpence(AssetDisposeBatchControllerGetImportStatisticRo.class);
   }
}


