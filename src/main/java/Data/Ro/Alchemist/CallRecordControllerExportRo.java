package Data.Ro.Alchemist;

import Data.ApisLayer.Alchemist.CallRecordController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class CallRecordControllerExportRo extends CallRecordController {

    @Param
    public String realName;
    @Param
    public String mobile;
    @Param
    public String startDate;
    @Param
    public String endDate;
    @Param
    public Integer taskId;
    @Param
    public String callType;
    @Param
    public Integer conclusion;
    @Param
    public String result;

   static {
      DenpendencesProcessor.roDendenpence(CallRecordControllerExportRo.class);
   }
}


