package Data.Ro.Alchemist;

import Data.ApisLayer.Alchemist.MarketResultController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;


@lombok.Data
public class MarketResultControllerMarketResultImportRo extends MarketResultController {

    @FileBody
    public String file;

   static {
      DenpendencesProcessor.roDendenpence(MarketResultControllerMarketResultImportRo.class);
   }
}


