package Data.Ro.Alchemist;

import Data.ApisLayer.Alchemist.SmsController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class SmsControllerPageRo extends SmsController {

    @Body
    public String realName;
    @Body
    public Integer current;
    @Body
    public Integer billId;
    @Body
    public String mobile;
    @Body
    public Integer pageSize;
    @Body
    public String createdTimeEnd;
    @Body
    public String createdTimeBegin;
    @Body
    public Integer templateId;
    @Body
    public Integer taskId;
    @Body
    public String status;

   static {
      DenpendencesProcessor.roDendenpence(SmsControllerPageRo.class);
   }
}


