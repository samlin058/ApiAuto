package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.LoanBatchController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationLibrary.Dependence;
import Service.Annotations.AnnotationLibrary.Dependences;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;

@Dependences({
        @Dependence(project = "indoArk", Class = "LoanSourceController", method = "sourceList", keys = "loanSourceName_LoanBatchControllerBatchAddRo")
})
@lombok.Data
public class LoanBatchControllerBatchAddRo extends LoanBatchController {

    @Body
    public String commissionOrg;
    @Body
    public String targetReturnRate;
    @Body
    public String commissionRate;
    @Body //默认查询的第一个来源
    public String loanSourceName = getConfigValue("$.data.[0].loanSourceName", "loanSourceName_LoanBatchControllerBatchAddRo");
    //(String) JsonHandler.readValueByJsonPath("$.data.[0].loanSourceName", TestContext.getCacheDataAsString("loanSourceName_LoanBatchControllerBatchAddRo"));
    @Body
    public String commissionDate;
    @Body
    public String remark;
    @Body
    public String withdrawDate;
    @Body
    public String batchNumber;

    static {
        DenpendencesProcessor.roDendenpence(LoanBatchControllerBatchAddRo.class);
    }

    public static void main(String[] args) {
        LoanBatchControllerBatchAddRo loanBatchControllerBatchAddRo = new LoanBatchControllerBatchAddRo();
        System.out.println(loanBatchControllerBatchAddRo);
    }
}


