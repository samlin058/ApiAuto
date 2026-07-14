package Data.ApisLayer.BlackShark;
import Data.Ro.BlackShark.AssetDisposeOpenControllerDisposeResultRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class AssetDisposeOpenController extends ApiBase {

    @Mapping(sub_path = "/blackShark/open/asset/dispose/result", requestType = "post" ,contentTpye = "Application/json")
    public String disposeResult (AssetDisposeOpenControllerDisposeResultRo ro) {
    return http(ro);
    }


}



