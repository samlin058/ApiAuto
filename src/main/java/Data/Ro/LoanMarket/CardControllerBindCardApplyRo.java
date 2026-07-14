package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.CardController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class CardControllerBindCardApplyRo extends CardController {

    static {
        DenpendencesProcessor.roDendenpence(CardControllerBindCardApplyRo.class);
    }

    @Body
    public String productCode;
    @Body
    public String cardBindType = "loan";
    @Body
    public String orderNo;
    @Body
    public String mobile;
    @Body
    public String cardNo;
    @Body
    public String taskId;

    public CardControllerBindCardApplyRo(String mobile, String productCode, String cardMum, String tastId) {
        this.mobile = mobile;
        this.productCode = productCode;
        this.cardNo = cardMum;
        this.taskId = tastId;
    }
}


