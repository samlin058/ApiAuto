package Data.Ro.Alchemist;

import Data.ApisLayer.Alchemist.MarketController;
import Service.Annotations.AnnotationLibrary.JsonArray;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;

@JsonArray
@lombok.Data
public class MarketControllerMarketImportRo extends MarketController {

    @Body
    public String rejectCode;
    @Body
    public String projectCode;
    @Body
    public String mobile;
    @Body
    public String name;
    @Body
    public String mobileMd5;
    @Body
    public String tag;
    @Body
    public String applyTime;
    @Body
    public String idNo;

   static {
      DenpendencesProcessor.roDendenpence(MarketControllerMarketImportRo.class);
   }
}


