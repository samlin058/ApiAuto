package Data.Apis.LoanMarket;
import Data.Ro.LoanMarket.UserOperateLogControllerSaveLogRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class UserOperateLogController extends ApiBase {

    @Mapping(sub_path = "/operate-log/add", requestType = "post" ,contentTpye = "application/json")
    public String saveLog (UserOperateLogControllerSaveLogRo ro) {
    return http(ro);
    }


}



