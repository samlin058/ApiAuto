package Data.Ro.BlackShark;

import Data.Apis.BlackShark.AssetDisposeBatchController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssetDisposeBatchControllerDidrkRo extends AssetDisposeBatchController {

    @Param
    public String magic;
    @Param
    public String id;

   static {
      DenpendencesProcessor.roDendenpence(AssetDisposeBatchControllerDidrkRo.class);
   }
}


