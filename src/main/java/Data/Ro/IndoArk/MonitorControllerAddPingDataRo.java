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
public class MonitorControllerAddPingDataRo extends MonitorController {

    @Body
    public String maxTime;
    @Body
    public String business;
    @Body
    public String avgTime;
    @Body
    public String packetLoss;
    @Body
    public String minTime;
    @Body
    public String startTime;
    @Body
    public String endTime;
    @Body
    public String stddevTime;
    @Body
    public String totalPackets;
    @Body
    public String operator;

   static {
      DenpendencesProcessor.roDendenpence(MonitorControllerAddPingDataRo.class);
   }
}


