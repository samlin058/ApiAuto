package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.LoanBatchController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class LoanBatchControllerWithDrawByCheckIdsRo extends LoanBatchController {

    @Body
    public List<?> importBatchIdList;

   static {
      DenpendencesProcessor.roDendenpence(LoanBatchControllerWithDrawByCheckIdsRo.class);
   }
}


