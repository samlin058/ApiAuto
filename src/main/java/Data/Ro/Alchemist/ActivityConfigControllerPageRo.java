package Data.Ro.Alchemist;

import Data.Apis.Alchemist.ActivityConfigController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class ActivityConfigControllerPageRo extends ActivityConfigController {

    @Param
    public String pageSize;
    @Param
    public String pageIndex;

   static {
      DenpendencesProcessor.roDendenpence(ActivityConfigControllerPageRo.class);
   }
}


