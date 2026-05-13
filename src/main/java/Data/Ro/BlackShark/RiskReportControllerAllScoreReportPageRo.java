package Data.Ro.BlackShark;

import Data.Apis.BlackShark.RiskReportController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class RiskReportControllerAllScoreReportPageRo extends RiskReportController {

    @Body
    public Integer current;
    @Body
    public Integer pageSize;
    @Body
    public String recordMonth;
    @Body
    public String title;
    @Body
    public String endMonth;
    @Body
    public String beginMonth;

   static {
      DenpendencesProcessor.roDendenpence(RiskReportControllerAllScoreReportPageRo.class);
   }
}


