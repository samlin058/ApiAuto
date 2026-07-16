package Data.ApisLayer.BlackShark;
import Data.Ro.BlackShark.AssetQueryOpenControllerQueryAssetRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class AssetQueryOpenController extends ApiBase {

    @Mapping(sub_path = "/blackShark/open/asset/detail", requestType = "post" ,contentTpye = "Application/json")
    public String queryAsset (AssetQueryOpenControllerQueryAssetRo ro) {
    return http(ro);
    }


}



