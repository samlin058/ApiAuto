package Data.Ro.BlackShark;

import Data.Apis.BlackShark.AssetDisposerController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssetDisposerControllerEffectivenessAssessmentRo extends AssetDisposerController {

    @Param
    public String disposerId;

   static {
      DenpendencesProcessor.roDendenpence(AssetDisposerControllerEffectivenessAssessmentRo.class);
   }
}


