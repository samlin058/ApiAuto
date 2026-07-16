package Data.Ro.LoanMarketRust;

import Data.ApisLayer.LoanMarketRust.channel_router_api;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationLibrary.JsonArray;
import Service.Annotations.AnnotationLibrary.PathParam;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;

import java.util.ArrayList;
import java.util.List;

@JsonArray
@lombok.Data
public class channel_router_apiModify_dispatch_ruleRo extends channel_router_api {

    static {
        DenpendencesProcessor.roDendenpence(channel_router_apiModify_dispatch_ruleRo.class);
    }

    @PathParam
    public Integer id_path;
    @Body
    public Integer weight;
    @Body
    public Integer partnerId;

    public static List<channel_router_apiModify_dispatch_ruleRo> getRo(){
        List<channel_router_apiModify_dispatch_ruleRo> list = new ArrayList<>();

        return list;
    }

    public static List<channel_router_apiModify_dispatch_ruleRo> setNull(String channelId){
        List<channel_router_apiModify_dispatch_ruleRo> list = new ArrayList<>();
        channel_router_apiModify_dispatch_ruleRo one = new channel_router_apiModify_dispatch_ruleRo();
        one.setId_path(Integer.parseInt(channelId));
        list.add(one);
        return list;
    }

}


