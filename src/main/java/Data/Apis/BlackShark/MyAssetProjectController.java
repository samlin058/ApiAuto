package Data.Apis.BlackShark;

import Data.Ro.BlackShark.MyAssetProjectControllerCreateRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class MyAssetProjectController extends ApiBase {

    @Mapping(sub_path = "/blackShark/assetProject/myProject/create", requestType = "post", contentTpye = "Application/json")
    public String create(MyAssetProjectControllerCreateRo ro) {
        return http(ro);
    }

}



