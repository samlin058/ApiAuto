package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.UserClockInController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class UserClockInControllerClockInRo extends UserClockInController {

    @Body
    public String action;
    @Body
    public String clockInTime;

   static {
      DenpendencesProcessor.roDendenpence(UserClockInControllerClockInRo.class);
   }
}


