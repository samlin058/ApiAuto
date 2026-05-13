package Data.Ro.Alchemist;

import Data.Apis.Alchemist.OperationController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class OperationControllerCrackRo extends OperationController {

    @Body
    public String channel;
    @Body
    public List<?> mobileMd5List;

   static {
      DenpendencesProcessor.roDendenpence(OperationControllerCrackRo.class);
   }
}


