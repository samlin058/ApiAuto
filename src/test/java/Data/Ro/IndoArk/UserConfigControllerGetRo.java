package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.UserConfigController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class UserConfigControllerGetRo extends UserConfigController {

    @Param
    public String type;

   static {
      DenpendencesProcessor.roDendenpence(UserConfigControllerGetRo.class);
   }
}


