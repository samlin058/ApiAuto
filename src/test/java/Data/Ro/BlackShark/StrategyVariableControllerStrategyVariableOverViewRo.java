package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.StrategyVariableController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class StrategyVariableControllerStrategyVariableOverViewRo extends StrategyVariableController {

    @Param
    public String Code;
    @Param
    public String Version;
    @Param
    public String bizTypeCode;
    @Param
    public String current;
    @Param
    public String pageSize;

   static {
      DenpendencesProcessor.roDendenpence(StrategyVariableControllerStrategyVariableOverViewRo.class);
   }
}


