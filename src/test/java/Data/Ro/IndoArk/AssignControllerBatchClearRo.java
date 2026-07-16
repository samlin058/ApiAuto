package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.AssignController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class AssignControllerBatchClearRo extends AssignController {

    @Body
    public List<?> loanIdList;

   static {
      DenpendencesProcessor.roDendenpence(AssignControllerBatchClearRo.class);
   }
}


