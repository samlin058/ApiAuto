package Data.Apis.BlackShark;

import Data.Ro.BlackShark.PreAssetPkgControllerAssetPkgBaseCreateRo;

import Data.Ro.BlackShark.PreAssetPkgControllerQueryBaseDataRo;
import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class PreAssetPkgController extends ApiBase {

    @Mapping(sub_path = "/blackShark/assetPkg/create", requestType = "post", contentTpye = "Application/json")
    public String assetPkgBaseCreate(PreAssetPkgControllerAssetPkgBaseCreateRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/blackShark/assetPkg/queryBaseData", requestType = "get")
    public String queryBaseData(PreAssetPkgControllerQueryBaseDataRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/blackShark/assetPkg/projectPkgList", requestType = "get")
    public String projectPkgList() {
        return http();
    }
}



