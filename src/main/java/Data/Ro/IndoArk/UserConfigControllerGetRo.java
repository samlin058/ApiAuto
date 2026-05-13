package Data.Ro.IndoArk;

import Data.Apis.IndoArk.UserConfigController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class UserConfigControllerGetRo extends UserConfigController {

    @Param
    public String type;

   static {
      DenpendencesProcessor.roDendenpence(UserConfigControllerGetRo.class);
   }
}


