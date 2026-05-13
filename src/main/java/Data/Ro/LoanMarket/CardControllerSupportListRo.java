package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.CardController;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class CardControllerSupportListRo extends CardController {

    static {
        DenpendencesProcessor.roDendenpence(CardControllerSupportListRo.class);
    }

    @Param
    public String productCode;
    @Param
    public String uid;

    public CardControllerSupportListRo(String productCode, String uid) {
        this.productCode = productCode;
        this.uid = uid;
    }
}


