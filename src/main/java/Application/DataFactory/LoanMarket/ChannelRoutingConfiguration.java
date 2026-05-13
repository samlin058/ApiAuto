package Application.DataFactory.LoanMarket;

import Data.Apis.LoanMarket.ChannelController;
import Data.Apis.LoanMarket.PartnerController;
import Data.Apis.LoanMarketRust.channel_router_api;
import Data.Ro.LoanMarket.ChannelControllerListRo;
import Data.Ro.LoanMarket.PartnerControllerListRo;
import Data.Ro.LoanMarketRust.*;
import Service.Util.Universal.JsonHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChannelRoutingConfiguration {
    private static ChannelController channelController = new ChannelController();
    private static channel_router_api apiService = new channel_router_api();
    private static PartnerController partnerController = new PartnerController();


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

    public static List<String> getPartnerIdList() {
        List<String> list = new ArrayList<>();
        String result = partnerController.list(new PartnerControllerListRo());
        String partnerList = JsonHandler.readArrayAsStringByJsonPath("$.data.data", result);
        // 从partnerList中提取所有对象的partnerCode值
        try {
            // 将JSON字符串转换为对象数组
            List<?> partners = JsonHandler.convertJsonStrToJsonArray(partnerList);
            for (Object partner : partners) {
                // 如果partner是ArrayList，说明是嵌套结构，需要进一步处理
                if (partner instanceof List) {
                    List<?> partnerItems = (List<?>) partner;
                    for (Object item : partnerItems) {
                        if (item != null && item.getClass().getName().contains("Map")) {
                            Map<?, ?> partnerMap = (Map<?, ?>) item;
                            Object partnerCodeObj = partnerMap.get("partnerCode");
                            if (partnerCodeObj != null) {
                                list.add(partnerCodeObj.toString());
                            }
                        }
                    }
                }
                // 如果partner直接是Map类型
                else if (partner != null && partner.getClass().getName().contains("Map")) {
                    Map<?, ?> partnerMap = (Map<?, ?>) partner;
                    Object partnerCodeObj = partnerMap.get("partnerCode");
                    if (partnerCodeObj != null) {
                        list.add(partnerCodeObj.toString());
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error extracting partnerCode values: " + e.getMessage());
        }
        return list;
    }

    ;

}
