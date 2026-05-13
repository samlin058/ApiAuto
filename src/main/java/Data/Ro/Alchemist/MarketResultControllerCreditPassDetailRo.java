package Data.Ro.Alchemist;

import Data.Apis.Alchemist.MarketResultController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class MarketResultControllerCreditPassDetailRo extends MarketResultController {

    @Param
    public String leadId;
    @Param
    public String current;
    @Param
    public String pageSize;

   static {
      DenpendencesProcessor.roDendenpence(MarketResultControllerCreditPassDetailRo.class);
   }
}


