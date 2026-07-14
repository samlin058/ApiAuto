package Data.Ro.Alchemist;

import Data.ApisLayer.Alchemist.SmsController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class SmsControllerPreviewRo extends SmsController {

    @Param
    public String templateId;

   static {
      DenpendencesProcessor.roDendenpence(SmsControllerPreviewRo.class);
   }
}


