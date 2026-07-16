package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.StrategyVariableController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class StrategyVariableControllerSaveOrUpdateStrategyRo extends StrategyVariableController {

    @Body
    public String bizTypeCode;
    @Body
    public String businessCode;
    @Body
    public String Code;
    @Body
    public String name;
    @Body
    public Integer strategyId;
    @Body
    public String type;
    @Body
    public String Version;
    @Body
    public String businessModuleCode;
    @Body
    public Object config;
    @Body
    public String paramCodes;

   static {
      DenpendencesProcessor.roDendenpence(StrategyVariableControllerSaveOrUpdateStrategyRo.class);
   }
}


