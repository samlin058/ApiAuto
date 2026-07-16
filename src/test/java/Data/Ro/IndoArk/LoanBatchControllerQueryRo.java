package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.LoanBatchController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class LoanBatchControllerQueryRo extends LoanBatchController {

    @Param
    public Integer current;
    @Param
    public Integer pageSize;
    @Param
    public String batchNumber;
    @Param
    public String commissionDateStart;
    @Param
    public String commissionDateEnd;
    @Param
    public String commissionOrg;
    @Param
    public String withdrawDateStart;
    @Param
    public String withdrawDateEnd;
    @Param
    public String loanSourceId;
    @Param
    public String totalCase;

   static {
      DenpendencesProcessor.roDendenpence(LoanBatchControllerQueryRo.class);
   }
}


