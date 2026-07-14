package Data.Ro.LoanMarketRust;

import Data.ApisLayer.LoanMarketRust.channel_router_api;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationLibrary.Dependence;
import Service.Annotations.AnnotationLibrary.PathParam;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Core.TestContext;
import Service.Util.Universal.JsonHandler;


@lombok.Data
@Dependence(project = "LoanMarketRust", Class = "router_api", method = "get_enable_partner_all", keys = "get_enable_partner_all")
public class channel_router_apiModify_router_priority_ruleRo extends channel_router_api {

    static {
        DenpendencesProcessor.roDendenpence(channel_router_apiModify_router_priority_ruleRo.class);
    }

    @PathParam
    public Integer id_path;
    @Body
    public Integer limit = 9999;
    @Body
    public Integer partnerId;
    @Body
    public Integer state = 1;
    @Body
    public Integer limitMode = 1;// 累计模式, 0 累计，1 每天, 2 按周 3 按月

    final static String get_enable_partner_all = JsonHandler.readArrayAsStringByJsonPath("$.data", TestContext.getCacheDataAsString("get_enable_partner_all"));

    public channel_router_apiModify_router_priority_ruleRo(String channelId, String partnerCode) {
        String partnerId = JsonHandler.findValueByKeyInArray(get_enable_partner_all, "code", partnerCode, "id");
        this.partnerId = Integer.parseInt(partnerId);
        this.id_path = Integer.parseInt(channelId);
    }
}


