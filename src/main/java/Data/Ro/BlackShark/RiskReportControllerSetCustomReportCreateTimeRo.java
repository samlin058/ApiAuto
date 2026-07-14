package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.RiskReportController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class RiskReportControllerSetCustomReportCreateTimeRo extends RiskReportController {

    @Body
    public Integer day;

   static {
      DenpendencesProcessor.roDendenpence(RiskReportControllerSetCustomReportCreateTimeRo.class);
   }
}


