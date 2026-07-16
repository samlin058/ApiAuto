package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.LoanBatchController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class LoanBatchControllerBatchUpdateRo extends LoanBatchController {

    @Body
    public String commissionOrg;
    @Body
    public String targetReturnRate;
    @Body
    public String commissionRate;
    @Body
    public String loanSourceName;
    @Body
    public String commissionDate;
    @Body
    public String remark;
    @Body
    public String id;
    @Body
    public String withdrawDate;
    @Body
    public String batchNumber;

   static {
      DenpendencesProcessor.roDendenpence(LoanBatchControllerBatchUpdateRo.class);
   }
}


