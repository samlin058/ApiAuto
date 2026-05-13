package Data.Apis.LoanMarket;
import Data.Ro.LoanMarket.TeleProductControllerListByChannelRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class TeleProductController extends ApiBase {

    @Mapping(sub_path = "/tele-product/channel/list", requestType = "get" )
    public String listByChannel (TeleProductControllerListByChannelRo ro) {
    return http(ro);
    }


}



