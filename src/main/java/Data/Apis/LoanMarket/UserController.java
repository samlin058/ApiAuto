package Data.Apis.LoanMarket;

import Data.Ro.LoanMarket.UserControllerAbandonReasonsRo;
import Data.Ro.LoanMarket.UserControllerLoginRo;

import Data.Ro.LoanMarket.UserControllerSendSmsRo;
import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class UserController extends ApiBase {

    @Mapping(sub_path = "/user/login", requestType = "post", contentTpye = "application/json")
    public String login(UserControllerLoginRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/user/abandon/reasons", requestType = "get" )
    public String abandonReasons (UserControllerAbandonReasonsRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/user/register/config", requestType = "get" )
    public String registerConfig() {
        return http();
    }


    @Mapping(sub_path = "/user/information-status", requestType = "get" )
    public String getInformationStatus() {
        return http();
    }

    /**
     * 发送绑卡申请时的验证码
     */
    @Mapping(sub_path = "/user/login-sms", requestType = "post" ,contentTpye = "application/json")
    public String sendSms (UserControllerSendSmsRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/user/summary-info", requestType = "get" )
    public String summaryInfo() {
        return http();
    }
}



