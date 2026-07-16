package Data.ApisLayer.Alchemist;
import Data.Ro.Alchemist.QueryControllerQueryAssignQueueRo;

import Data.Ro.Alchemist.QueryControllerQueryTaskResultsRo;



import Data.Ro.Alchemist.QueryControllerCaseBaseInfoRo;

import Data.Ro.Alchemist.QueryControllerQueryAssignmentsRo;

import Data.Ro.Alchemist.QueryControllerMobileRo;



import Data.Ro.Alchemist.QueryControllerQueryAutoProcessJobRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class QueryController extends ApiBase {

    @Mapping(sub_path = "/query/smsTemplateConfigs", requestType = "get" )
    public String queryAssignQueue (QueryControllerQueryAssignQueueRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/query/taskResults", requestType = "get" )
    public String queryTaskResults (QueryControllerQueryTaskResultsRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/query/allTaskResults", requestType = "get" )
    public String allTaskResults() {
    return http();
    }


    @Mapping(sub_path = "/query/billBaseInfo", requestType = "get" )
    public String caseBaseInfo (QueryControllerCaseBaseInfoRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/query/assignments", requestType = "get" )
    public String queryAssignments (QueryControllerQueryAssignmentsRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/query/mobile", requestType = "get" )
    public String mobile (QueryControllerMobileRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/query/mobile/batch", requestType = "post" ,contentTpye = "Application/json")
    public String mobileBatch() {
    return http();
    }


    @Mapping(sub_path = "/query/queryAutoProcessJob", requestType = "get" )
    public String queryAutoProcessJob (QueryControllerQueryAutoProcessJobRo ro) {
    return http(ro);
    }


}



