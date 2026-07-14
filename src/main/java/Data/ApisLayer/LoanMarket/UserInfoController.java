package Data.ApisLayer.LoanMarket;

import Data.Ro.LoanMarket.UserInfoControllerAddRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class UserInfoController extends ApiBase {

    @Mapping(sub_path = "/user-info/save", requestType = "post", contentTpye = "application/json")
    public String add(UserInfoControllerAddRo ro) {
        return http(ro);
    }


}



