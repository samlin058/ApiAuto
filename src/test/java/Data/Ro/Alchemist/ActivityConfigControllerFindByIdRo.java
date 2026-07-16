package Data.Ro.Alchemist;

import Data.ApisLayer.Alchemist.ActivityConfigController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class ActivityConfigControllerFindByIdRo extends ActivityConfigController {

    @Param
    public Integer id;

   static {
      DenpendencesProcessor.roDendenpence(ActivityConfigControllerFindByIdRo.class);
   }
}


