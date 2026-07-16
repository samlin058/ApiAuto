package Data.ApisLayer.IndoArk;
import Data.Ro.IndoArk.ReservedControllerCheckRo;





import Data.Ro.IndoArk.ReservedControllerQueryReservedApplyRo;

import Data.Ro.IndoArk.ReservedControllerQueryReservedApproveRecordRo;

import Data.Ro.IndoArk.ReservedControllerQueryReservedTaskRo;

import Data.Ro.IndoArk.ReservedControllerQueryReservedHistRo;

import Data.Ro.IndoArk.ReservedControllerExportRo;

import Data.Ro.IndoArk.ReservedControllerStartUnOutstandingRo;

import Data.Ro.IndoArk.ReservedControllerExecuteRo;

import Data.Ro.IndoArk.ReservedControllerExecuteApproveRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class ReservedController extends ApiBase {

    @Mapping(sub_path = "/reserve/check", requestType = "get" )
    public String check (ReservedControllerCheckRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/reserve/approveStatus", requestType = "get" )
    public String getApproveStatus() {
    return http();
    }


    @Mapping(sub_path = "/reserve/reservedResult", requestType = "get" )
    public String getReservedResult() {
    return http();
    }


    @Mapping(sub_path = "/reserve/query/reservedApply", requestType = "get" )
    public String queryReservedApply (ReservedControllerQueryReservedApplyRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/reserve/query/getReservedApproveRecord", requestType = "get" )
    public String queryReservedApproveRecord (ReservedControllerQueryReservedApproveRecordRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/reserve/query/reservedTask", requestType = "get" )
    public String queryReservedTask (ReservedControllerQueryReservedTaskRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/reserve/query/reservedHist", requestType = "get" )
    public String queryReservedHist (ReservedControllerQueryReservedHistRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/reserve/reservedExport", requestType = "get" )
    public String export (ReservedControllerExportRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/reserve/startUnOutstanding", requestType = "post" ,contentTpye = "application/json")
    public String startUnOutstanding (ReservedControllerStartUnOutstandingRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/reserve/execute", requestType = "post" ,contentTpye = "application/json")
    public String execute (ReservedControllerExecuteRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/reserve/approve", requestType = "post" ,contentTpye = "application/json")
    public String executeApprove (ReservedControllerExecuteApproveRo ro) {
    return http(ro);
    }


}



