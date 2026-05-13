package Data.Apis.LoanMarket;
import Data.Ro.LoanMarket.PartnerControllerListRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class PartnerController extends ApiBase {

    @Mapping(sub_path = "/admin/partner/list", requestType = "get" )
    public String list (PartnerControllerListRo ro) {
    return http(ro);
    }


}



