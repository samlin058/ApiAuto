package Data.Apis.IndoArk;
import Data.Ro.IndoArk.UserConfigControllerGetRo;

import Data.Ro.IndoArk.UserConfigControllerSaveRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class UserConfigController extends ApiBase {

    @Mapping(sub_path = "/user/config/get", requestType = "get" )
    public String get (UserConfigControllerGetRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/user/config/save", requestType = "post" ,contentTpye = "application/json")
    public String save (UserConfigControllerSaveRo ro) {
    return http(ro);
    }


}



