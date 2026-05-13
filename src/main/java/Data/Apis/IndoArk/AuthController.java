package Data.Apis.IndoArk;
import Data.Ro.IndoArk.AuthControllerQueryRo;

import Data.Ro.IndoArk.AuthControllerQueryUrlRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class AuthController extends ApiBase {

    @Mapping(sub_path = "/auth-service/auth/query", requestType = "get" )
    public String query (AuthControllerQueryRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/auth-service/auth/queryUrl", requestType = "get" )
    public String queryUrl (AuthControllerQueryUrlRo ro) {
    return http(ro);
    }


}



