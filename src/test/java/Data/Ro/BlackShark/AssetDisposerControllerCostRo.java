package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.AssetDisposerController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssetDisposerControllerCostRo extends AssetDisposerController {

    @Param
    public String dateType;
    @Param
    public String bizScope;

   static {
      DenpendencesProcessor.roDendenpence(AssetDisposerControllerCostRo.class);
   }
}


