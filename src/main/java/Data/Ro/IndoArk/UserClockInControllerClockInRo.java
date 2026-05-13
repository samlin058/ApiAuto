package Data.Ro.IndoArk;

import Data.Apis.IndoArk.UserClockInController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class UserClockInControllerClockInRo extends UserClockInController {

    @Body
    public String action;
    @Body
    public String clockInTime;

   static {
      DenpendencesProcessor.roDendenpence(UserClockInControllerClockInRo.class);
   }
}


