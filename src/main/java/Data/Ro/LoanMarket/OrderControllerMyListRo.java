package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.OrderController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class OrderControllerMyListRo extends OrderController {

   static {
      DenpendencesProcessor.roDendenpence(OrderControllerMyListRo.class);
   }

    @Param
    public String current;
    @Param
    public String pageSize;


}


