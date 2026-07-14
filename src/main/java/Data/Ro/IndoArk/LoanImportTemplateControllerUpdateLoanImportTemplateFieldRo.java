package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.LoanImportTemplateController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class LoanImportTemplateControllerUpdateLoanImportTemplateFieldRo extends LoanImportTemplateController {

    @Param
    public Integer id;
    @Param
    public String fieldId;

   static {
      DenpendencesProcessor.roDendenpence(LoanImportTemplateControllerUpdateLoanImportTemplateFieldRo.class);
   }
}


