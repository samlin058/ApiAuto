package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.RepayPlanController;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class RepayPlanControllerMyRepayPlanRo extends RepayPlanController {

    static {
        DenpendencesProcessor.roDendenpence(RepayPlanControllerMyRepayPlanRo.class);
    }

    @Param
    public String orderId;


    public RepayPlanControllerMyRepayPlanRo(String orderId) {
        this.orderId = orderId;
    }
}


