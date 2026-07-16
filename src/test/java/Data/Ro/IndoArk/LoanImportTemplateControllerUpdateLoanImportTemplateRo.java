package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.LoanImportTemplateController;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class LoanImportTemplateControllerUpdateLoanImportTemplateRo extends LoanImportTemplateController {

    @Param
    public Integer id;


   static {
      DenpendencesProcessor.roDendenpence(LoanImportTemplateControllerUpdateLoanImportTemplateRo.class);
   }
}


