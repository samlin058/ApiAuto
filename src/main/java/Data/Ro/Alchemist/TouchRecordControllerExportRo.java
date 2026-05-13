package Data.Ro.Alchemist;

import Data.Apis.Alchemist.TouchRecordController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class TouchRecordControllerExportRo extends TouchRecordController {

    @Param
    public String[] realname;
    @Param
    public String mobile;
    @Param
    public String billId;
    @Param
    public String startDate;
    @Param
    public String endDate;
    @Param
    public String activityId;
    @Param
    public String pageIndex;
    @Param
    public String pageSize;
    @Param
    public String bizType;

   static {
      DenpendencesProcessor.roDendenpence(TouchRecordControllerExportRo.class);
   }
}


