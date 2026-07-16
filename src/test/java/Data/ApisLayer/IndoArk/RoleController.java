package Data.ApisLayer.IndoArk;

import Data.Ro.IndoArk.*;
import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class RoleController extends ApiBase {

    @Mapping(sub_path = "/role/list", requestType = "get" )
    public String list (RoleControllerListRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/role/configResources", requestType = "post" ,contentTpye = "application/json")
    public String configResources (RoleControllerConfigResourcesRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/role/assignedUserList", requestType = "post" ,contentTpye = "application/json")
    public String assignedUserList (RoleControllerAssignedUserListRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/role/assignUsers", requestType = "post" ,contentTpye = "application/json")
    public String assignUsers (RoleControllerAssignUsersRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/role/queryOne", requestType = "get" )
    public String queryOne (RoleControllerQueryOneRo ro) {
    return http(ro);
    }


}



