package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.RoleController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class RoleControllerQueryOneRo extends RoleController {

    @Param
    public String id;

   static {
      DenpendencesProcessor.roDendenpence(RoleControllerQueryOneRo.class);
   }
}


