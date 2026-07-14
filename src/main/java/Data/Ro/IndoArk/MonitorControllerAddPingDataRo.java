package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.MonitorController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


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


