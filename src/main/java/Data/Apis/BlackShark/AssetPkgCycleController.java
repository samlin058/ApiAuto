package Data.Apis.BlackShark;
import Data.Ro.BlackShark.AssetPkgCycleControllerListByPkgIdsRo;

import Data.Ro.BlackShark.AssetPkgCycleControllerSaveRo;
import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class AssetPkgCycleController extends ApiBase {

    @Mapping(sub_path = "/blackShark/asset/cycle/listByPkgIds", requestType = "get" )
    public String listByPkgIds (AssetPkgCycleControllerListByPkgIdsRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/asset/cycle/save", requestType = "post" ,contentTpye = "application/json")
    public String save (AssetPkgCycleControllerSaveRo ro) {
        return http(ro);
    }
}



