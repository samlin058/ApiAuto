package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.LoanSourceController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class LoanSourceControllerSourceAddRo extends LoanSourceController {

    @Body
    public String loanSourceName;
    @Body
    public String loanSourceCode;

   static {
      DenpendencesProcessor.roDendenpence(LoanSourceControllerSourceAddRo.class);
   }
}


