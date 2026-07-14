package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.CardController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class CardControllerVerifyCardRo extends CardController {

    static {
        DenpendencesProcessor.roDendenpence(CardControllerVerifyCardRo.class);
    }

    @Body
    public String verifyCode;
    @Body
    public String taskId;


    public CardControllerVerifyCardRo(String tastId) {
        this.taskId = tastId;
        this.verifyCode = "888888";
    }

    public CardControllerVerifyCardRo(String tastId, String verifyCode) {
        this.taskId = tastId;
        this.verifyCode = verifyCode;
    }
}


