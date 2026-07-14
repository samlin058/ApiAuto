package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.LoanImportController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class LoanImportControllerDownloadFailFileRo extends LoanImportController {

    @Param
    public Integer id;

   static {
      DenpendencesProcessor.roDendenpence(LoanImportControllerDownloadFailFileRo.class);
   }
}


