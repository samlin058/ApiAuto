package Data.Ro.BlackShark;

import Data.Apis.BlackShark.StrategyResultPrecentConfigController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class StrategyResultPrecentConfigControllerAddResultPercentConfigRo extends StrategyResultPrecentConfigController {

    @Body
    public Integer id;
    @Body
    public List<?> results;
    @Body
    public String strategyType;

   static {
      DenpendencesProcessor.roDendenpence(StrategyResultPrecentConfigControllerAddResultPercentConfigRo.class);
   }
}


