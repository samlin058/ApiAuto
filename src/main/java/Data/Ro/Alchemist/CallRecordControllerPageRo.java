package Data.Ro.Alchemist;

import Data.ApisLayer.Alchemist.CallRecordController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class CallRecordControllerPageRo extends CallRecordController {

    @Body
    public Integer conclusion;
    @Body
    public String result;
    @Body
    public String realName;
    @Body
    public String endDate;
    @Body
    public String mobile;
    @Body
    public String startDate;
    @Body
    public Integer taskId;
    @Body
    public String callType;

   static {
      DenpendencesProcessor.roDendenpence(CallRecordControllerPageRo.class);
   }
}


