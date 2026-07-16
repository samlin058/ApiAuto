package Data.ApisLayer.LoanMarket;
import Data.Ro.LoanMarket.CreditControllerGetMethodNameRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class CreditController extends ApiBase {

    @Mapping(sub_path = "/admin/credit/list", requestType = "get" )
    public String list (CreditControllerGetMethodNameRo ro) {
    return http(ro);
    }


}



