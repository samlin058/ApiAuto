package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.LoanImportController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class LoanImportControllerImportContactRo extends LoanImportController {

    @FileBody
    public String file;
    @Body
    public Integer loanSourceId;

   static {
      DenpendencesProcessor.roDendenpence(LoanImportControllerImportContactRo.class);
   }
}


