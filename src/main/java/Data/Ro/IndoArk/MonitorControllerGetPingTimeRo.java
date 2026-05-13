package Data.Ro.IndoArk;

import Data.Apis.IndoArk.MonitorController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class MonitorControllerGetPingTimeRo extends MonitorController {

    @Param
    public Number startTime;
    @Param
    public Number endTime;

   static {
      DenpendencesProcessor.roDendenpence(MonitorControllerGetPingTimeRo.class);
   }
}


