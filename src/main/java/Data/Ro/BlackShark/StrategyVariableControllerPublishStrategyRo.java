package Data.Ro.BlackShark;

import Data.Apis.BlackShark.StrategyVariableController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class StrategyVariableControllerPublishStrategyRo extends StrategyVariableController {

    @Body
    public String bizTypeCode;
    @Body
    public String businessCode;
    @Body
    public String Code;
    @Body
    public String businessModuleCode;
    @Body
    public String Version;

   static {
      DenpendencesProcessor.roDendenpence(StrategyVariableControllerPublishStrategyRo.class);
   }
}


