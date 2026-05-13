package Data.Ro.Alchemist;

import Data.Apis.Alchemist.QueryController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class QueryControllerQueryAssignQueueRo extends QueryController {

    @Param
    public String pageSize;
    @Param
    public String pageIndex;

   static {
      DenpendencesProcessor.roDendenpence(QueryControllerQueryAssignQueueRo.class);
   }
}


