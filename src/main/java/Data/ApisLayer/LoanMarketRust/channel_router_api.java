package Data.ApisLayer.LoanMarketRust;

import Data.Ro.LoanMarketRust.*;
import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;

import java.util.List;


public class channel_router_api extends ApiBase {

    @Mapping(sub_path = "/channels/{id}/routers/info", requestType = "get")
    public String get_router_info(channel_router_apiGet_router_infoRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/channels/{id}/routers/router-partner", requestType = "post", contentTpye = "application/json")
    public String modify_router_parther(List<channel_router_apiModify_router_partherRo> ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/channels/{id}/routers/router-partner", requestType = "get")
    public String get_router_parther(channel_router_apiGet_router_partherRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/channels/{id}/routers/priority-rule", requestType = "post", contentTpye = "application/json")
    public String modify_router_priority_rule(channel_router_apiModify_router_priority_ruleRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/channels/{id}/routers/router-rule", requestType = "post", contentTpye = "application/json")
    public String modify_router_rule(List<channel_router_apiModify_router_ruleRo> ro) {
        return http(ro);
    }


    @Mapping(sub_path = "/channels/{id}/routers/dispatch-rule", requestType = "post", contentTpye = "application/json")
    public String modify_dispatch_rule(List<channel_router_apiModify_dispatch_ruleRo> ro) {
        return http(ro);
    }


}



