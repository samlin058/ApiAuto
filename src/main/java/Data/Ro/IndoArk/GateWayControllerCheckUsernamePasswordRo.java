package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.GateWayController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class GateWayControllerCheckUsernamePasswordRo extends GateWayController {

    @Param
    public String username;
    @Param
    public String password;

   static {
      DenpendencesProcessor.roDendenpence(GateWayControllerCheckUsernamePasswordRo.class);
   }
}


