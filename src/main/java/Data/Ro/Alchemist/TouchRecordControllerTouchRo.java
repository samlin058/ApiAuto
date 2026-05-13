package Data.Ro.Alchemist;

import Data.Apis.Alchemist.TouchRecordController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class TouchRecordControllerTouchRo extends TouchRecordController {

    @Body
    public String bizType;
    @Body
    public String touchType;
    @Body
    public String shortCode;

   static {
      DenpendencesProcessor.roDendenpence(TouchRecordControllerTouchRo.class);
   }
}


