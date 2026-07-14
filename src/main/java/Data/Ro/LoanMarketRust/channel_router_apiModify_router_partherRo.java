package Data.Ro.LoanMarketRust;

import Data.ApisLayer.LoanMarketRust.channel_router_api;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationLibrary.Dependence;
import Service.Annotations.AnnotationLibrary.JsonArray;
import Service.Annotations.AnnotationLibrary.PathParam;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Core.TestContext;
import Service.Util.Universal.JsonHandler;
import Service.Util.Universal.StringHandler;
import Service.Util.Universal.TimestampHandler;

import java.util.ArrayList;
import java.util.List;

@JsonArray
@lombok.Data
@Dependence(project = "LoanMarketRust", Class = "router_api", method = "get_enable_partner_all", keys = "get_enable_partner_all")
public class channel_router_apiModify_router_partherRo extends channel_router_api {

    static {
        DenpendencesProcessor.roDendenpence(channel_router_apiModify_router_partherRo.class);
    }

    @PathParam
    public Integer id_path;
    @Body
    public Long id;
    @Body
    public Integer partnerId;
    @Body
    public String partnerName;
    @Body
    public Integer channelId;
    @Body
    public Integer aliasChannelId;
    @Body
    public Integer state = 1;
    @Body
    public Integer useNum = 0;

    final static String get_enable_partner_all = JsonHandler.readArrayAsStringByJsonPath("$.data",TestContext.getCacheDataAsString("get_enable_partner_all"));

    static channel_router_api apiService = new channel_router_api();

    public static List<channel_router_apiModify_router_partherRo> getRo(String partnerCode, String channelId) {
        String partnerName = JsonHandler.findValueByKeyInArray(get_enable_partner_all, "code", partnerCode, "name");
        if (!hasParther(partnerName) || StringHandler.isEmpty(channelId)) {
            return null;
        }
        int channel_id = Integer.parseInt(channelId);
        List<channel_router_apiModify_router_partherRo> list = new ArrayList<>();
        channel_router_apiModify_router_partherRo oneData = new channel_router_apiModify_router_partherRo();
        oneData.setPartnerName(partnerName);
        oneData.setId_path(channel_id);
        oneData.setChannelId(channel_id);
        oneData.setAliasChannelId(channel_id);
        if (hasParther(channelId, partnerName)) {
            String channelRoutingConfig = apiService.get_router_info(new channel_router_apiGet_router_infoRo(channelId));
            String routerPartners = JsonHandler.readArrayAsStringByJsonPath("$.data.routerPartners", channelRoutingConfig);
            String id = JsonHandler.findValueByKeyInArray(routerPartners, "partnerName", partnerName, "id");
            oneData.setId(Long.parseLong(id));
        } else {
            oneData.setId(TimestampHandler.getCurrentTimestamp());
        }
        String id = JsonHandler.findValueByKeyInArray(get_enable_partner_all, "name", partnerName, "id");
        oneData.setPartnerId(Integer.parseInt(id));
        list.add(oneData);
        return list;
    }

    /**
     * 设置多个资方
     *
     * @param partnerNameList 资方名单
     * @param channelId       渠道id
     * @return 入参
     */
    public static List<channel_router_apiModify_router_partherRo> getRo(List<String> partnerNameList, String channelId) {
        List<channel_router_apiModify_router_partherRo> list = new ArrayList<>();


        return list;
    }

    /**
     * 渠道是否有配置路由资方
     *
     * @param channelId   渠道id
     * @param partnerName 资方名
     * @return 是或否
     */
    private static Boolean hasParther(String channelId, String partnerName) {
        Boolean flag = false;
        String channelConfig = apiService.get_router_info(new channel_router_apiGet_router_infoRo(channelId));
        String partnerCode = JsonHandler.findValueByKeyInArray(JsonHandler.readArrayAsStringByJsonPath("$.data.routerPartners", channelConfig), "partnerName", partnerName, "partnerCode");
        if (!StringHandler.isEmpty(partnerCode)) {
            flag = true;
        }
        return flag;
    }

    /**
     * 数据库中是否存在该资方
     *
     * @param partnerName 资方名
     * @return 是或否
     */
    private static Boolean hasParther(String partnerName) {
        return !StringHandler.isEmpty(JsonHandler.findValueByKeyInArray(get_enable_partner_all, "name", partnerName, "code"));
    }


}


