package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.UserConfigController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class UserConfigControllerSaveRo extends UserConfigController {

    @Body
    public String type;
    @Body
    public String content;

   static {
      DenpendencesProcessor.roDendenpence(UserConfigControllerSaveRo.class);
   }
}


