package Data.Ro.Alchemist;

import Data.Apis.Alchemist.QueryController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class QueryControllerCaseBaseInfoRo extends QueryController {

    @Param
    public Integer billId;

   static {
      DenpendencesProcessor.roDendenpence(QueryControllerCaseBaseInfoRo.class);
   }
}


