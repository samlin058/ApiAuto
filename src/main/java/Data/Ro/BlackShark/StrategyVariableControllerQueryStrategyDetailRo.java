package Data.Ro.BlackShark;

import Data.Apis.BlackShark.StrategyVariableController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class StrategyVariableControllerQueryStrategyDetailRo extends StrategyVariableController {

    @Param
    public String Code;
    @Param
    public String Version;
    @Body
    public String businessCode;
    @Body
    public String code;
    @Body
    public String businessModuleCode;
    @Body
    public String version;

   static {
      DenpendencesProcessor.roDendenpence(StrategyVariableControllerQueryStrategyDetailRo.class);
   }
}


