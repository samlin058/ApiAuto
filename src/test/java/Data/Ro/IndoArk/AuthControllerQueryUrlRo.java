package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.AuthController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AuthControllerQueryUrlRo extends AuthController {

    @Param
    public String handler;
    @Param
    public String invokeAppName;

   static {
      DenpendencesProcessor.roDendenpence(AuthControllerQueryUrlRo.class);
   }
}


