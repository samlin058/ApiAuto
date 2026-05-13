package Data.Ro.Alchemist;

import Data.Apis.Alchemist.ActivityConfigController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class ActivityConfigControllerAllRo extends ActivityConfigController {

    @Param
    public String status;

   static {
      DenpendencesProcessor.roDendenpence(ActivityConfigControllerAllRo.class);
   }
}


