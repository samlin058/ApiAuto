package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.CreditLimitController;
import Service.Annotations.AnnotationLibrary.Header;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class CreditLimitControllerDispatchRo extends CreditLimitController {

    static {
        DenpendencesProcessor.roDendenpence(CreditLimitControllerDispatchRo.class);
    }

    @Header
    public String X_Login_Uid;


    public CreditLimitControllerDispatchRo(String uid) {
        this.X_Login_Uid = uid;
    }
}


