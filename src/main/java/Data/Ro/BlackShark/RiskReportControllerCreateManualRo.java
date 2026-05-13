package Data.Ro.BlackShark;

import Data.Apis.BlackShark.RiskReportController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class RiskReportControllerCreateManualRo extends RiskReportController {

    @Body
    public String reportType;
    @Body
    public String recordMonth;

   static {
      DenpendencesProcessor.roDendenpence(RiskReportControllerCreateManualRo.class);
   }
}


