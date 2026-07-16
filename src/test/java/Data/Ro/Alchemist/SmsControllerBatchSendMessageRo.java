package Data.Ro.Alchemist;

import Data.ApisLayer.Alchemist.SmsController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class SmsControllerBatchSendMessageRo extends SmsController {

    @Body
    public List<?> billIds;
    @Body
    public Integer templateId;

   static {
      DenpendencesProcessor.roDendenpence(SmsControllerBatchSendMessageRo.class);
   }
}


