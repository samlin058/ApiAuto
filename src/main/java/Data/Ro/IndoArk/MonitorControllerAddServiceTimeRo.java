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
public class MonitorControllerAddServiceTimeRo extends MonitorController {

    @Body
    public String business;
    @Body
    public String method;
    @Body
    public String responseTime;
    @Body
    public String startTime;
    @Body
    public String api;
    @Body
    public String endTime;
    @Body
    public String operator;

   static {
      DenpendencesProcessor.roDendenpence(MonitorControllerAddServiceTimeRo.class);
   }
}


