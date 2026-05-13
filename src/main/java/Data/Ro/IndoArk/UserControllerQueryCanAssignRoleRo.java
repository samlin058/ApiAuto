package Data.Ro.IndoArk;

import Data.Apis.IndoArk.UserController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class UserControllerQueryCanAssignRoleRo extends UserController {

    @Param
    public String account;

   static {
      DenpendencesProcessor.roDendenpence(UserControllerQueryCanAssignRoleRo.class);
   }
}


