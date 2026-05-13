package Data.Ro.IndoArk;

import Data.Apis.IndoArk.LoanStateController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class LoanStateControllerGetStateRo extends LoanStateController {

    @Param
    public String loanId;
    @Body
    public String reason;
    @Body
    public String expireDate;
    @Body
    public String value;
    @Body
    public String effectiveDate;


   static {
      DenpendencesProcessor.roDendenpence(LoanStateControllerGetStateRo.class);
   }
}


