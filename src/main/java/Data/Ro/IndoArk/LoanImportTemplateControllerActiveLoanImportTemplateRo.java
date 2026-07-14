package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.LoanImportTemplateController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class LoanImportTemplateControllerActiveLoanImportTemplateRo extends LoanImportTemplateController {

    @Param
    public Integer id;

   static {
      DenpendencesProcessor.roDendenpence(LoanImportTemplateControllerActiveLoanImportTemplateRo.class);
   }
}


