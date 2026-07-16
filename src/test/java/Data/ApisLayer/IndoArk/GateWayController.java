package Data.ApisLayer.IndoArk;
import Data.Ro.IndoArk.GateWayControllerIsChildRo;

import Data.Ro.IndoArk.GateWayControllerCheckUsernamePasswordRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class GateWayController extends ApiBase {

    @Mapping(sub_path = "/auth-service/gateWay/user/isChild", requestType = "get" )
    public String isChild (GateWayControllerIsChildRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/auth-service/gateWay/user/passwordCheck", requestType = "get" )
    public String checkUsernamePassword (GateWayControllerCheckUsernamePasswordRo ro) {
    return http(ro);
    }


}



