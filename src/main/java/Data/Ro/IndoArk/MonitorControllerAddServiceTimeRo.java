package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.MonitorController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


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


