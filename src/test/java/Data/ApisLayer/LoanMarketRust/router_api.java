package Data.ApisLayer.LoanMarketRust;


import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class router_api extends ApiBase {

    @Mapping(sub_path = "/routers/partner/all", requestType = "get")
    public String get_enable_partner_all() {
        return http();
    }


    @Mapping(sub_path = "/routers/channel/all", requestType = "get")
    public String get_enable_channel_all() {
        return http();
    }


}



