package Data.ApisLayer.LoanMarket;

import Data.Ro.LoanMarket.ChannelControllerListRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class ChannelController extends ApiBase {

    @Mapping(sub_path = "/admin/channel/list", requestType = "get")
    public String list(ChannelControllerListRo ro) {
        return http(ro);
    }

}



