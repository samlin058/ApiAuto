package Data.Apis.Alchemist;




import Data.Ro.Alchemist.CustomerControllerUserActionRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class CustomerController extends ApiBase {

    @Mapping(sub_path = "/customers/userRegister", requestType = "post" ,contentTpye = "Application/x-www-form-urlencoded")
    public String userRegister() {
    return http();
    }


    @Mapping(sub_path = "/customers/userInlet", requestType = "post" ,contentTpye = "Application/json")
    public String userInlet() {
    return http();
    }


    @Mapping(sub_path = "/customers/userAction", requestType = "post" ,contentTpye = "Application/json")
    public String userAction (CustomerControllerUserActionRo ro) {
    return http(ro);
    }


}



