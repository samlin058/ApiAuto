package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.LoanBatchController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class LoanBatchControllerExportByConditionRo extends LoanBatchController {

    @Param
    public String batchNumber;
    @Param
    public String commissionDateStart;
    @Param
    public String commissionDateEnd	;
    @Param
    public String commissionOrg	;
    @Param
    public String withdrawDateStart	;
    @Param
    public String withdrawDateEnd	;
    @Param
    public String loanSourceId;

   static {
      DenpendencesProcessor.roDendenpence(LoanBatchControllerExportByConditionRo.class);
   }
}


