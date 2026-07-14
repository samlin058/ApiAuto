package Data.ApisLayer.IndoArk;
import Data.Ro.IndoArk.UserControllerQueryCanAssignRoleRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class UserController extends ApiBase {

    @Mapping(sub_path = "/user/role/canAssign", requestType = "get" )
    public String queryCanAssignRole (UserControllerQueryCanAssignRoleRo ro) {
    return http(ro);
    }


}



