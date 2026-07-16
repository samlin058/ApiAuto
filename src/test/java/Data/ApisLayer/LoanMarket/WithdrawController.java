package Data.ApisLayer.LoanMarket;
import Data.Ro.LoanMarket.WithdrawControllerListRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class WithdrawController extends ApiBase {

    @Mapping(sub_path = "/admin/withdrawal/list", requestType = "get" )
    public String list (WithdrawControllerListRo ro) {
    return http(ro);
    }


}



