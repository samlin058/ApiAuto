package Data.Apis.BlackShark;

import Data.Ro.BlackShark.ProjectUserControllerAddUserRo;
import Data.Ro.BlackShark.ProjectUserControllerQueryRo;
import Data.Ro.BlackShark.ProjectUserControllerRemoveUserRo;
import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class ProjectUserController extends ApiBase {
    @Mapping(sub_path = "/blackShark/dacl/project/user/query", requestType = "post", contentTpye = "application/json")
    public String query(ProjectUserControllerQueryRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/blackShark/dacl/project/user/addUser", requestType = "post", contentTpye = "Application/json")
    public String addUser(ProjectUserControllerAddUserRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/blackShark/dacl/project/user/removeUser", requestType = "post", contentTpye = "application/json")
    public String removeUser(ProjectUserControllerRemoveUserRo ro) {
        return http(ro);
    }


}



