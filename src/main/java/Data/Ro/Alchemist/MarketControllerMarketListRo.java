package Data.Ro.Alchemist;

import Data.Apis.Alchemist.MarketController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class MarketControllerMarketListRo extends MarketController {

    @Body
    public Integer current;
    @Body
    public String applyTimeEnd;
    @Body
    public String projectCode;
    @Body
    public String applyTimeBegin;
    @Body
    public Integer pageSize;
    @Body
    public Boolean cracked;

   static {
      DenpendencesProcessor.roDendenpence(MarketControllerMarketListRo.class);
   }
}


