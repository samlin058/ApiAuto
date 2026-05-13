package Data.Ro.BlackShark;

import Data.Apis.BlackShark.StrategyVariableController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class StrategyVariableControllerStrategyImportRo extends StrategyVariableController {

    @FileBody
    public String file;
    @Body
    public String strategyCodeMappings;

   static {
      DenpendencesProcessor.roDendenpence(StrategyVariableControllerStrategyImportRo.class);
   }
}


