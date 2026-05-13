package Data.Ro.BlackShark;

import Data.Apis.BlackShark.QualityInspectionController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class QualityInspectionControllerUpdateSensitiveWordsRo extends QualityInspectionController {

    @Body
    public Integer id;
    @Body
    public String content;

   static {
      DenpendencesProcessor.roDendenpence(QualityInspectionControllerUpdateSensitiveWordsRo.class);
   }
}


