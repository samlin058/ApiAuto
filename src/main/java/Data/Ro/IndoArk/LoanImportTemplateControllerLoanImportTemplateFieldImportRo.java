package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.LoanImportTemplateController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class LoanImportTemplateControllerLoanImportTemplateFieldImportRo extends LoanImportTemplateController {

    @Param
    public Integer id;
    @FileBody
    public String file;

   static {
      DenpendencesProcessor.roDendenpence(LoanImportTemplateControllerLoanImportTemplateFieldImportRo.class);
   }
}


