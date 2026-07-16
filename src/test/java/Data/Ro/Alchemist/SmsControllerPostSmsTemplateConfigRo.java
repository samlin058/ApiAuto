package Data.Ro.Alchemist;

import Data.ApisLayer.Alchemist.SmsController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class SmsControllerPostSmsTemplateConfigRo extends SmsController {

    @Body
    public Integer activityId;
    @Body
    public Integer businessId;
    @Body
    public Integer id;
    @Body
    public Integer templateId;
    @Body
    public String status;

   static {
      DenpendencesProcessor.roDendenpence(SmsControllerPostSmsTemplateConfigRo.class);
   }
}


