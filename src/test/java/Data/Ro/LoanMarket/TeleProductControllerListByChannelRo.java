package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.TeleProductController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class TeleProductControllerListByChannelRo extends TeleProductController {

   static {
      DenpendencesProcessor.roDendenpence(TeleProductControllerListByChannelRo.class);
   }

    @Param
    public String channel;


}


