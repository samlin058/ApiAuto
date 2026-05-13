package Data.Apis.BlackShark;
import Data.Ro.BlackShark.ExternalRepayControllerPlanRollBackRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class ExternalRepayController extends ApiBase {

    @Mapping(sub_path = "/blackShark/external/repay/plan/rollback", requestType = "post" ,contentTpye = "Application/json")
    public String planRollBack (ExternalRepayControllerPlanRollBackRo ro) {
    return http(ro);
    }


}



