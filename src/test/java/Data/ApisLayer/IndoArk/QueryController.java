package Data.ApisLayer.IndoArk;
import Data.Ro.IndoArk.QueryControllerGetQueryAssignmentsBelongToMeRo;

import Data.Ro.IndoArk.QueryControllerExportQueryAssignmentsBelongToMeRo;

import Data.Ro.IndoArk.QueryControllerGetQueryAssignmentsAmountRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class QueryController extends ApiBase {

    @Mapping(sub_path = "/query/assignments/manager", requestType = "get" )
    public String getQueryAssignmentsBelongToMe (QueryControllerGetQueryAssignmentsBelongToMeRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/query/assignments/manager/export", requestType = "get" )
    public String exportQueryAssignmentsBelongToMe (QueryControllerExportQueryAssignmentsBelongToMeRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/query/assignments/manager/summary", requestType = "get" )
    public String getQueryAssignmentsAmount (QueryControllerGetQueryAssignmentsAmountRo ro) {
    return http(ro);
    }


}



