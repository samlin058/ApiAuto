package Data.Ro.BlackShark;

import Data.Apis.BlackShark.StrategyVariableController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class StrategyVariableControllerResultListRo extends StrategyVariableController {

    @Param
    public String strategyTypeCode;

   static {
      DenpendencesProcessor.roDendenpence(StrategyVariableControllerResultListRo.class);
   }
}


