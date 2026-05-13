package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.OrderController;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class OrderControllerDetailRo extends OrderController {

   static {
      DenpendencesProcessor.roDendenpence(OrderControllerDetailRo.class);
   }

    @Param
    public String orderId;


    public OrderControllerDetailRo(String orderId) {
        this.orderId=orderId;
    }
}


