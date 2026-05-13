package Data.Apis.LoanMarket;

import Data.Ro.LoanMarket.AssessmentControllerInitRo;

import Data.Ro.LoanMarket.AssessmentControllerResultRo;
import Data.Ro.LoanMarket.AssessmentControllerStatusRo;
import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class AssessmentController extends ApiBase {

    @Mapping(sub_path = "/credit-assessment/new", requestType = "post", contentTpye = "application/json")
    public String init(AssessmentControllerInitRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/credit-assessment/status", requestType = "get")
    public String status(AssessmentControllerStatusRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/credit-assessment/result", requestType = "get")
    public String result(AssessmentControllerResultRo ro) {
        return http(ro);
    }

}



