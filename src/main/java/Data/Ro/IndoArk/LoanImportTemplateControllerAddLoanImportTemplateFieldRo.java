package Data.Ro.IndoArk;

import Data.Apis.IndoArk.LoanImportTemplateController;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class LoanImportTemplateControllerAddLoanImportTemplateFieldRo extends LoanImportTemplateController {

    @Param
    public Integer id;
    @Param
    public Integer current;
    @Param
    public Integer pageSize;

   static {
      DenpendencesProcessor.roDendenpence(LoanImportTemplateControllerAddLoanImportTemplateFieldRo.class);
   }
}


