package Data.ApisLayer.LoanMarket;

import Data.Ro.LoanMarket.TestKaniuControllerKaNiuLoanConfirmServiceRo;
import Data.Ro.LoanMarket.TestKaniuControllerKaNiuRepayConfirmServiceRo;
import Data.Ro.LoanMarket.TestKaniuControllerKaNiuUserCheckServiceRo;

import Data.Ro.LoanMarket.TestKaniuControllerKaNiuUserCreditServiceRo;
import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class TestKaniuController extends ApiBase {

    @Mapping(sub_path = "/test/open/channel/kaniu/fund/api/v1/user-check", requestType = "post", contentTpye = "application/json")
    public String kaNiuUserCheckService(TestKaniuControllerKaNiuUserCheckServiceRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/test/open/channel/kaniu/fund/api/v1/user-credit", requestType = "post", contentTpye = "application/json")
    public String kaNiuUserCreditService(TestKaniuControllerKaNiuUserCreditServiceRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/test/open/channel/kaniu/fund/api/v1/loan-confirm", requestType = "post", contentTpye = "application/json")
    public String KaNiuLoanConfirmService(TestKaniuControllerKaNiuLoanConfirmServiceRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/test/open/channel/kaniu/fund/api/v1/repay-confirm", requestType = "post", contentTpye = "application/json")
    public String KaNiuRepayConfirmService(TestKaniuControllerKaNiuRepayConfirmServiceRo ro) {
        return http(ro);
    }


}



