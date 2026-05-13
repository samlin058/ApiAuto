package Data.Apis.IndoArk;
import Data.Ro.IndoArk.AssignControllerManualRo;

import Data.Ro.IndoArk.AssignControllerAssignImportRo;

import Data.Ro.IndoArk.AssignControllerPreQueryRo;

import Data.Ro.IndoArk.AssignControllerPreAssignByAgentRo;

import Data.Ro.IndoArk.AssignControllerPreAssignByGroupRo;

import Data.Ro.IndoArk.AssignControllerBatchClearRo;

import Data.Ro.IndoArk.AssignControllerManualAssistRo;

import Data.Ro.IndoArk.AssignControllerQueryRo;





import Data.Ro.IndoArk.AssignControllerAssignFileFailDownloadRo;

import Data.Ro.IndoArk.AssignControllerQueryExportRo;





import Data.Ro.IndoArk.AssignControllerGetAssignRecordsRo;

import Data.Ro.IndoArk.AssignControllerQueryClearRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class AssignController extends ApiBase {

    @Mapping(sub_path = "/loan/assign/manual", requestType = "post" ,contentTpye = "application/json")
    public String manual (AssignControllerManualRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/loan/assign/file", requestType = "post" ,contentTpye = "multipart/form-data")
    public String assignImport (AssignControllerAssignImportRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/loan/assign/pre/query", requestType = "get" )
    public String preQuery (AssignControllerPreQueryRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/loan/assign/pre/assignByAgent", requestType = "post" ,contentTpye = "application/json")
    public String preAssignByAgent (AssignControllerPreAssignByAgentRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/loan/assign/pre/assignByGroup", requestType = "post" ,contentTpye = "application/json")
    public String preAssignByGroup (AssignControllerPreAssignByGroupRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/loan/assign/clear", requestType = "post" ,contentTpye = "application/json")
    public String batchClear (AssignControllerBatchClearRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/loan/assign/manualAssist", requestType = "post" ,contentTpye = "application/json")
    public String manualAssist (AssignControllerManualAssistRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/loan/assign/query", requestType = "get" )
    public String query (AssignControllerQueryRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/loan/assign/file/lastResult", requestType = "get" )
    public String assignFileLastResult() {
    return http();
    }


    @Mapping(sub_path = "/loan/assign/file/template", requestType = "get" )
    public String assignFileLastTemplate() {
    return http();
    }


    @Mapping(sub_path = "/loan/assign/file/failDownload", requestType = "get" )
    public String assignFileFailDownload (AssignControllerAssignFileFailDownloadRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/loan/assign/query/export", requestType = "get" )
    public String queryExport (AssignControllerQueryExportRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/loan/assign/agentGroup", requestType = "get" )
    public String queryAgentGroup() {
    return http();
    }


    @Mapping(sub_path = "/loan/assign/loanState", requestType = "get" )
    public String queryLoanState() {
    return http();
    }


    @Mapping(sub_path = "/loan/assign/{loanId}/records", requestType = "get" )
    public String getAssignRecords (AssignControllerGetAssignRecordsRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/loan/assign/queryClear", requestType = "post" ,contentTpye = "application/json")
    public String queryClear (AssignControllerQueryClearRo ro) {
    return http(ro);
    }


}



