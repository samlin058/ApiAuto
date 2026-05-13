package Data.Ro.Alchemist;

import Data.Apis.Alchemist.MarketResultController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class MarketResultControllerMarketResultDownloadRo extends MarketResultController {

    @Param
    public String fileRecordId;

   static {
      DenpendencesProcessor.roDendenpence(MarketResultControllerMarketResultDownloadRo.class);
   }
}


