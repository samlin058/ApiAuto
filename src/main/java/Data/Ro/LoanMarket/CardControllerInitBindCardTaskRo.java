package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.CardController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class CardControllerInitBindCardTaskRo extends CardController {

    static {
        DenpendencesProcessor.roDendenpence(CardControllerInitBindCardTaskRo.class);
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

    public CardControllerInitBindCardTaskRo(String mobile, String productCode, String cardNum) {
        this.mobile = mobile;
        this.productCode = productCode;
        this.cardNo = cardNum;

    }

    public CardControllerInitBindCardTaskRo(String mobile, String productCode, String cardNum ,String cardBindType) {
        this.cardBindType = cardBindType;
        this.mobile = mobile;
        this.productCode = productCode;
        this.cardNo = cardNum;
    }
}


