package Data.Ro.IndoArk;

import Data.Apis.IndoArk.GateWayController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


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


