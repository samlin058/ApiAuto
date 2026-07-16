package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.RoleController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class RoleControllerConfigResourcesRo extends RoleController {

    @Body
    public String resources;
    @Body
    public Integer id;

   static {
      DenpendencesProcessor.roDendenpence(RoleControllerConfigResourcesRo.class);
   }
}


