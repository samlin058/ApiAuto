package Data.Ro.BlackShark;

import Data.Apis.BlackShark.RiskReportController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class RiskReportControllerRiskReportContextRo extends RiskReportController {

    @Param
    public String[] reportId;

   static {
      DenpendencesProcessor.roDendenpence(RiskReportControllerRiskReportContextRo.class);
   }
}


