package Data.Ro.Alchemist;

import Data.Apis.Alchemist.QueryController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class QueryControllerQueryTaskResultsRo extends QueryController {

    @Param
    public String pageIndex;
    @Param
    public String pageSize;

   static {
      DenpendencesProcessor.roDendenpence(QueryControllerQueryTaskResultsRo.class);
   }
}


