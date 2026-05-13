package Data.Ro.Alchemist;

import Data.Apis.Alchemist.MarketController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class MarketControllerBatchCrackRo extends MarketController {

    @Body
    public String applyTimeEnd;
    @Body
    public List<?> channels;
    @Body
    public String projectCode;
    @Body
    public String applyTimeBegin;
    @Body
    public Boolean cracked;

   static {
      DenpendencesProcessor.roDendenpence(MarketControllerBatchCrackRo.class);
   }
}


