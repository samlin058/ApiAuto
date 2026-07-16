package Data.ApisLayer.IndoArk;

import Data.Ro.IndoArk.*;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class LoanBatchController extends ApiBase {

    @Mapping(sub_path = "/loan/batch/add", requestType = "post", contentTpye = "application/json")
    public String batchAdd(LoanBatchControllerBatchAddRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/loan/batch/list", requestType = "get")
    public String batchList() {
        return http();
    }


    @Mapping(sub_path = "/loan/batch/query", requestType = "get")
    public String query(LoanBatchControllerQueryRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/loan/batch/update", requestType = "post", contentTpye = "application/json")
    public String batchUpdate(LoanBatchControllerBatchUpdateRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/loan/batch/withDrawByCondition", requestType = "post", contentTpye = "application/json")
    public String withDrawByCondition(LoanBatchControllerWithDrawByConditionRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/loan/batch/withDrawByCheckIds", requestType = "post", contentTpye = "application/json")
    public String withDrawByCheckIds(LoanBatchControllerWithDrawByCheckIdsRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/loan/batch/delete", requestType = "post", contentTpye = "application/json")
    public String delete(LoanBatchControllerDeleteRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/loan/batch/Interaction/exportByCondition", requestType = "get")
    public String exportByCondition(LoanBatchControllerExportByConditionRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/loan/batch/Interaction/exportByCheckIds", requestType = "get")
    public String exportByCheckIds(LoanBatchControllerExportByCheckIdsRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/loan/batch/commissionOrgList", requestType = "get")
    public String commissionOrgList() {
        return http();
    }
    
    @Mapping(sub_path = "/loan/batch/source/{loanSourceId}", requestType = "get")
    public String batchListBySource() {
        return http();
    }

}



