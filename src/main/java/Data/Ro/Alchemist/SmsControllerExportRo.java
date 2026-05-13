package Data.Ro.Alchemist;

import Data.Apis.Alchemist.SmsController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class SmsControllerExportRo extends SmsController {

    @Param
    public String realName;
    @Param
    public String billId;
    @Param
    public String status;
    @Param
    public String mobile;
    @Param
    public String templateId;
    @Param
    public String createdTimeBegin;
    @Param
    public String createdTimeEnd;
    @Param
    public String taskId;

   static {
      DenpendencesProcessor.roDendenpence(SmsControllerExportRo.class);
   }
}


