package Data.Ro.BlackShark;

import Data.Apis.BlackShark.AssetController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssetControllerTestGraphqlRo extends AssetController {

    @Param
    public String assetId;

   static {
      DenpendencesProcessor.roDendenpence(AssetControllerTestGraphqlRo.class);
   }
}


