package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.LoanBatchController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class LoanBatchControllerWithDrawByConditionRo extends LoanBatchController {

    @Body
    public String commissionOrg;
    @Body
    public String withdrawDateEnd;
    @Body
    public String commissionDateStart;
    @Body
    public String withdrawDateStart;
    @Body
    public String commissionDateEnd;
    @Body
    public Integer loanSourceId;
    @Body
    public String batchNumber;

   static {
      DenpendencesProcessor.roDendenpence(LoanBatchControllerWithDrawByConditionRo.class);
   }
}


