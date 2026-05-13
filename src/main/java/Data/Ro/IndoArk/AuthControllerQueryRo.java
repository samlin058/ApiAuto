package Data.Ro.IndoArk;

import Data.Apis.IndoArk.AuthController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class AuthControllerQueryRo extends AuthController {

    @Param
    public String handler;
    @Param
    public String invokeAppName;

   static {
      DenpendencesProcessor.roDendenpence(AuthControllerQueryRo.class);
   }
}


