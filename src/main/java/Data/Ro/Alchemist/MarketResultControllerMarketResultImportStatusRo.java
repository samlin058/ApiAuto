package Data.Ro.Alchemist;

import Data.ApisLayer.Alchemist.MarketResultController;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;



@lombok.Data
public class MarketResultControllerMarketResultImportStatusRo extends MarketResultController {

    @Param
    public String fileRecordId;

   static {
      DenpendencesProcessor.roDendenpence(MarketResultControllerMarketResultImportStatusRo.class);
   }
}


