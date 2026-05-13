package Data.Apis.IndoArk;
import Data.Ro.IndoArk.AuthUserControllerQueryOneByAccountRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class AuthUserController extends ApiBase {

    @Mapping(sub_path = "/auth-service/user/one", requestType = "get" )
    public String queryOneByAccount (AuthUserControllerQueryOneByAccountRo ro) {
    return http(ro);
    }


}



