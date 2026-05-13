package Data.Ro.BlackShark;

import Data.Apis.BlackShark.AssetDisposerController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssetDisposerControllerGradeRo extends AssetDisposerController {

    @Param
    public String agencyType;

   static {
      DenpendencesProcessor.roDendenpence(AssetDisposerControllerGradeRo.class);
   }
}


