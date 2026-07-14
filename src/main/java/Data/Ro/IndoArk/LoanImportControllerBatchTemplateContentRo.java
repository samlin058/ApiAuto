package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.LoanImportController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class LoanImportControllerBatchTemplateContentRo extends LoanImportController {

    @Param
    public Integer loanId;

   static {
      DenpendencesProcessor.roDendenpence(LoanImportControllerBatchTemplateContentRo.class);
   }
}


