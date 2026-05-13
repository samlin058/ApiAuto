package Data.Ro.Alchemist;

import Data.Apis.Alchemist.QueryController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class QueryControllerQueryAssignmentsRo extends QueryController {

    @Param
    public Integer touched;
    @Param
    public String latestActiveStartTime;
    @Param
    public String latestActiveEndTime;
    @Param
    public String pageIndex;
    @Param
    public String pageSize;
    @Param
    public String activityId;
    @Param
    public String registerStartTime;
    @Param
    public String registerEndTime;
    @Param
    public String inletStartTime;
    @Param
    public String inletEndTime;

   static {
      DenpendencesProcessor.roDendenpence(QueryControllerQueryAssignmentsRo.class);
   }
}


