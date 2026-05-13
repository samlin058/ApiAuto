package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.OrderController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class OrderControllerCardReconfirmRo extends OrderController {

    static {
        DenpendencesProcessor.roDendenpence(OrderControllerCardReconfirmRo.class);
    }

    @Body
    public String verifyCode;
    @Body
    public Long orderId;


    public OrderControllerCardReconfirmRo(String orderId, String verifyCode) {
        this.orderId = Long.parseLong(orderId);
        this.verifyCode = verifyCode;
    }
}


