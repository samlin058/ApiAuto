package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.LoanBatchController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class LoanBatchControllerDeleteRo extends LoanBatchController {

    @Body
    public Integer importBatchId;

   static {
      DenpendencesProcessor.roDendenpence(LoanBatchControllerDeleteRo.class);
   }
}


