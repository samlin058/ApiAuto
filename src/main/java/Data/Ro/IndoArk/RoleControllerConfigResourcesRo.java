package Data.Ro.IndoArk;

import Data.Apis.IndoArk.RoleController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


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


