package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.RepayPlanController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class RepayPlanControllerPaymentRo extends RepayPlanController {

   static {
      DenpendencesProcessor.roDendenpence(RepayPlanControllerPaymentRo.class);
   }

    @Body
    public String orderId;
    @Body
    public Integer cardId;
    @Body
    public Integer periods;
    @Body
    public Integer repayAmt;
    @Body
    public Boolean settleAll;

    public RepayPlanControllerPaymentRo() {

    }

    public RepayPlanControllerPaymentRo(String orderId, Integer cardId, Integer periods, Integer repayAmt, Boolean settleAll) {
        this.orderId = orderId;
        this.cardId = cardId;
        this.periods = periods;
        this.repayAmt = repayAmt;
        this.settleAll = settleAll;
    }
}


