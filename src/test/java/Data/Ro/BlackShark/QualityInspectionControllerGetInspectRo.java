package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.QualityInspectionController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class QualityInspectionControllerGetInspectRo extends QualityInspectionController {

    @Param
    public String connId;

   static {
      DenpendencesProcessor.roDendenpence(QualityInspectionControllerGetInspectRo.class);
   }
}


