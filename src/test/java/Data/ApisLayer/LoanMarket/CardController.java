package Data.ApisLayer.LoanMarket;


import Data.Ro.LoanMarket.*;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class CardController extends ApiBase {

    @Mapping(sub_path = "/card/check", requestType = "post")
    public String check() {
        return http();
    }


    @Mapping(sub_path = "/card/bind-info", requestType = "get")
    public String bindInfo(CardControllerBindInfoRo ro) {
        return http(ro);
    }

    /**
     * 查询支持的银行机构
     */
    @Mapping(sub_path = "/card/support-list", requestType = "get")
    public String supportList(CardControllerSupportListRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/card/init-card-task", requestType = "post", contentTpye = "application/json")
    public String initBindCardTask(CardControllerInitBindCardTaskRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/card/bind-card-apply", requestType = "post", contentTpye = "application/json")
    public String bindCardApply(CardControllerBindCardApplyRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/card/verify", requestType = "post", contentTpye = "application/json")
    public String verifyCard(CardControllerVerifyCardRo ro) {
        return http(ro);
    }
}



