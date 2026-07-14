package Data.ApisLayer.BlackShark;
import Data.Ro.BlackShark.StrategyResultPrecentConfigControllerAddResultPercentConfigRo;
import java.util.List;
import Data.Ro.BlackShark.StrategyResultPrecentConfigControllerFindConfigsRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class StrategyResultPrecentConfigController extends ApiBase {

    @Mapping(sub_path = "/blackShark/strategyResult/addResultPercentConfig", requestType = "post" ,contentTpye = "Application/json")
    public String addResultPercentConfig(List<StrategyResultPrecentConfigControllerAddResultPercentConfigRo> ro) {
    return http(ro);
    }


    @Mapping(sub_path = "/blackShark/strategyResult/findConfigs", requestType = "get" )
    public String findConfigs (StrategyResultPrecentConfigControllerFindConfigsRo ro) {
    return http(ro);
    }


}



