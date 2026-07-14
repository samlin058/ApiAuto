package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.SmsRecordController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class SmsRecordControllerSmsSendRo extends SmsRecordController {

    @Param
    public String mobile;
    @Param
    public String templateName;
    @Param
    public String sendStartTime;
    @Param
    public String sendEndTime;
    @Param
    public Integer sendStatus;
    @Param
    public Integer current;
    @Param
    public Integer pageSize;
    @Param
    public String batchNumber;
    @Param
    public String contractNumber;
    @Param
    public Integer batchId;

   static {
      DenpendencesProcessor.roDendenpence(SmsRecordControllerSmsSendRo.class);
   }
}


