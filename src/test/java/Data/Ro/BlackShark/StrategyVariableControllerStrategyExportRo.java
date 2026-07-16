package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.StrategyVariableController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class StrategyVariableControllerStrategyExportRo extends StrategyVariableController {

    @Param
    public String businessCode;
    @Param
    public String businessModuleCode;
    @Param
    public String Code;
    @Param
    public String Version;
    @Param
    public String bizTypeCode;

   static {
      DenpendencesProcessor.roDendenpence(StrategyVariableControllerStrategyExportRo.class);
   }
}


