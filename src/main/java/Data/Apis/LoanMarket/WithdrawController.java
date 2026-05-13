package Data.Apis.LoanMarket;
import Data.Ro.LoanMarket.WithdrawControllerListRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class WithdrawController extends ApiBase {

    @Mapping(sub_path = "/admin/withdrawal/list", requestType = "get" )
    public String list (WithdrawControllerListRo ro) {
    return http(ro);
    }


}



