package Data.BusinessLayer.LoanMarket;

import Data.ApisLayer.LoanMarket.ChannelController;
import Data.ApisLayer.LoanMarketRust.channel_router_api;
import Data.Ro.LoanMarket.ChannelControllerListRo;
import Data.Ro.LoanMarketRust.channel_router_apiGet_router_infoRo;
import Data.Ro.LoanMarketRust.channel_router_apiModify_router_partherRo;
import Data.Ro.LoanMarketRust.channel_router_apiModify_router_priority_ruleRo;
import Service.Util.Universal.JsonHandler;

public class ChannelRoutingConfiguration {
    private static ChannelController channelController = new ChannelController();
    private static channel_router_api apiService = new channel_router_api();


    public static void main(String[] args) {
        changeCapital("luandaoTest2", "NIWODAI");
    }

    /**
     * 更改渠道的资方，只走这资方
     *
     * @param channelCode 渠道名
     * @param partnerCode 资方code
     */
    public static void changeCapital(String channelCode, String partnerCode) {
        // 1、根据渠道明获取id
        String ChannelListStr = channelController.list(new ChannelControllerListRo(channelCode));
        String channelId = JsonHandler.findValueByKeyInArray(JsonHandler.readArrayAsJsonStringByJsonPath("$.data.data", ChannelListStr), "channelCode", channelCode, "id");
        // 2、根据渠道id获取当前路由配置
        apiService.get_router_info(new channel_router_apiGet_router_infoRo(channelId));
        // 3、配置渠道可路由资方配置
        apiService.modify_router_parther(channel_router_apiModify_router_partherRo.getRo(partnerCode, channelId));
        // 4、设置渠道优先规则
        apiService.modify_router_priority_rule(new channel_router_apiModify_router_priority_ruleRo(channelId, partnerCode));
        System.out.println("当前资方code："+partnerCode);
    }

}
