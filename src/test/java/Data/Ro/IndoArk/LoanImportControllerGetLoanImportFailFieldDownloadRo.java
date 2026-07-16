package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.LoanImportController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class LoanImportControllerGetLoanImportFailFieldDownloadRo extends LoanImportController {

    @Param
    public String importBatchId;
    @Param
    public String importRecordId;

   static {
      DenpendencesProcessor.roDendenpence(LoanImportControllerGetLoanImportFailFieldDownloadRo.class);
   }
}


