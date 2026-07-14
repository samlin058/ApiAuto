package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.StrategyResultPrecentConfigController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class StrategyResultPrecentConfigControllerFindConfigsRo extends StrategyResultPrecentConfigController {

    @Param
    public String[] ids;

   static {
      DenpendencesProcessor.roDendenpence(StrategyResultPrecentConfigControllerFindConfigsRo.class);
   }
}


