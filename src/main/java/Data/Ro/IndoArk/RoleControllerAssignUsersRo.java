package Data.Ro.IndoArk;

import Data.Apis.IndoArk.RoleController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class RoleControllerAssignUsersRo extends RoleController {

    @Body
    public String Code;
    @Body
    public Integer id;
    @Body
    public String type;
    @Body
    public String users;

   static {
      DenpendencesProcessor.roDendenpence(RoleControllerAssignUsersRo.class);
   }
}


