package Data.ApisLayer.BlackShark;
import Data.Ro.BlackShark.AssetControllerQueryAssetByQlRo;

import Data.Ro.BlackShark.AssetControllerTestGraphqlRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class AssetController extends ApiBase {

    @Mapping(sub_path = "/blackShark/asset/queryAssetByQl", requestType = "post" ,contentTpye = "Application/json")
    public String queryAssetByQl (AssetControllerQueryAssetByQlRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/asset/testGraphql", requestType = "get" )
    public String testGraphql (AssetControllerTestGraphqlRo ro) {
    return http(ro);
    }


}



