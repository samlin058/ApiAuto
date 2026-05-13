package Data.Ro.Alchemist;

import Data.Apis.Alchemist.TouchRecordController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class TouchRecordControllerPageRo extends TouchRecordController {

    @Body
    public Integer activityId;
    @Body
    public String endDate;
    @Body
    public Integer pageIndex;
    @Body
    public Integer billId;
    @Body
    public String mobile;
    @Body
    public Integer pageSize;
    @Body
    public String startDate;
    @Body
    public Integer taskId;
    @Body
    public String realname;

   static {
      DenpendencesProcessor.roDendenpence(TouchRecordControllerPageRo.class);
   }
}


