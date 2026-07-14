package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.LoanImportTemplateController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class LoanImportTemplateControllerGetLoanFieldsRo extends LoanImportTemplateController {

    @Param
    public String id;

   static {
      DenpendencesProcessor.roDendenpence(LoanImportTemplateControllerGetLoanFieldsRo.class);
   }
}


