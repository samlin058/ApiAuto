package Data.Apis.BlackShark;

import Data.Ro.BlackShark.AssetProjectControllerQueryRo;
import Data.Ro.BlackShark.AssetProjectControllerProjectOverviewDetailRo;
import Data.Ro.BlackShark.AssetProjectControllerProcessStateRateRo;
import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class AssetProjectController extends ApiBase {

    @Mapping(sub_path = "/blackShark/assetProject/query", requestType = "post", contentTpye = "Application/json")
    public String query(AssetProjectControllerQueryRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/blackShark/assetProject/projectTypeList", requestType = "get")
    public String projectTypeList() {
        return http();
    }

    @Mapping(sub_path = "/blackShark/assetProject/projectOverviewDetail", requestType = "get")
    public String projectOverviewDetail(AssetProjectControllerProjectOverviewDetailRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/blackShark/assetProject/processStateRate", requestType = "get")
    public String processStateRate(AssetProjectControllerProcessStateRateRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/blackShark/assetProject/list", requestType = "get")
    public String list() {
        return http();
    }


}



