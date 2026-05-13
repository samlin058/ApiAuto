package Data.Ro.Alchemist;

import Data.Apis.Alchemist.QueryController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class QueryControllerQueryAutoProcessJobRo extends QueryController {

    @Param
    public Integer taskId;

   static {
      DenpendencesProcessor.roDendenpence(QueryControllerQueryAutoProcessJobRo.class);
   }
}


