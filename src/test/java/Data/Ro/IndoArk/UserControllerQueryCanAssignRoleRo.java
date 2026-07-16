package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.UserController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class UserControllerQueryCanAssignRoleRo extends UserController {

    @Param
    public String account;

   static {
      DenpendencesProcessor.roDendenpence(UserControllerQueryCanAssignRoleRo.class);
   }
}


