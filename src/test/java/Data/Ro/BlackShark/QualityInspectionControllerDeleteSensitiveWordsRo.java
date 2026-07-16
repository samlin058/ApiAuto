package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.QualityInspectionController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class QualityInspectionControllerDeleteSensitiveWordsRo extends QualityInspectionController {

    @Body
    public Integer id;

   static {
      DenpendencesProcessor.roDendenpence(QualityInspectionControllerDeleteSensitiveWordsRo.class);
   }
}


