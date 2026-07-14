package Data.ApisLayer.LoanMarket;


import Data.Ro.LoanMarket.CreditLimitControllerDispatchRo;
import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class CreditLimitController extends ApiBase {

    @Mapping(sub_path = "/credit-limit/best", requestType = "get")
    public String dispatch(CreditLimitControllerDispatchRo ro) {
        return http(ro);
    }


}



