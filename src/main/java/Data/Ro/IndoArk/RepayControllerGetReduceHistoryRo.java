package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.RepayController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class RepayControllerGetReduceHistoryRo extends RepayController {

    @Param
    public Integer loanId;

   static {
      DenpendencesProcessor.roDendenpence(RepayControllerGetReduceHistoryRo.class);
   }
}


