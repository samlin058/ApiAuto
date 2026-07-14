package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.RepayPlanController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class RepayPlanControllerPlanCalculateRo extends RepayPlanController {

    static {
        DenpendencesProcessor.roDendenpence(RepayPlanControllerPlanCalculateRo.class);
    }

    @Body
    public String productCode;
    @Body
    public Integer periods;
    @Body
    public Integer loanAmt;
    @Body
    public boolean benefit = false;


    public RepayPlanControllerPlanCalculateRo(String productCode, Integer periods, Integer loanAmt) {
        this.productCode = productCode;
        this.periods = periods;
        this.loanAmt = loanAmt;
    }

    public RepayPlanControllerPlanCalculateRo(String uid, String productCode, Integer periods, Integer loanAmt) {
        RepayPlanControllerPlanCalculateRo.header.put("X-Login-Uid", uid);
        this.productCode = productCode;
        this.periods = periods;
        this.loanAmt = loanAmt;
    }

    public RepayPlanControllerPlanCalculateRo(String productCode, Integer periods, Integer loanAmt, Boolean benefit) {
        this.productCode = productCode;
        this.periods = periods;
        this.loanAmt = loanAmt;
        this.benefit = benefit;
    }
}


