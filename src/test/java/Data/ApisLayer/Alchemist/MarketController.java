package Data.ApisLayer.Alchemist;
import Data.Ro.Alchemist.MarketControllerMarketImportRo;
import java.util.List;
import Data.Ro.Alchemist.MarketControllerMarketListRo;

import Data.Ro.Alchemist.MarketControllerBatchCrackRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class MarketController extends ApiBase {

    @Mapping(sub_path = "/market/import", requestType = "post" ,contentTpye = "Application/json")
    public String marketImport(List<MarketControllerMarketImportRo> ro) {
    return http(ro);
    }


    @Mapping(sub_path = "/market/list", requestType = "post" ,contentTpye = "Application/json")
    public String marketList (MarketControllerMarketListRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/market/crack/batch", requestType = "post" ,contentTpye = "Application/json")
    public String batchCrack (MarketControllerBatchCrackRo ro) {
    return http(ro);
    }


}



