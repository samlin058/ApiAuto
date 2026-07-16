package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.CardController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class CardControllerBindInfoRo extends CardController {

   static {
      DenpendencesProcessor.roDendenpence(CardControllerBindInfoRo.class);
   }

    @Param
    public String productCode;
    @Param
    public String cardBindType;


}


