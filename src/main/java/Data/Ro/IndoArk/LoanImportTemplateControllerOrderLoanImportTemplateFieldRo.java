package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.LoanImportTemplateController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class LoanImportTemplateControllerOrderLoanImportTemplateFieldRo extends LoanImportTemplateController {

    @Param
    public Integer id;
    @Param
    public String Code;
    @Param
    public String index;

   static {
      DenpendencesProcessor.roDendenpence(LoanImportTemplateControllerOrderLoanImportTemplateFieldRo.class);
   }
}


