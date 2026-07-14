package Data.ApisLayer.IndoArk;
import Data.Ro.IndoArk.RepayControllerApplyListRo;

import Data.Ro.IndoArk.RepayControllerGetReduceHistoryRo;

import Data.Ro.IndoArk.RepayControllerApproveRo;

import Data.Ro.IndoArk.RepayControllerExcelImportRo;



import Data.Ro.IndoArk.RepayControllerDownloadFailFileRo;



import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class RepayController extends ApiBase {

    @Mapping(sub_path = "/repay/list", requestType = "get" ,contentTpye = "application/json")
    public String applyList (RepayControllerApplyListRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/repay/history", requestType = "get" ,contentTpye = "application/json")
    public String getReduceHistory (RepayControllerGetReduceHistoryRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/repay/approve", requestType = "post" ,contentTpye = "application/json")
    public String approve (RepayControllerApproveRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/repay/import", requestType = "post" ,contentTpye = "multipart/form-data")
    public String excelImport (RepayControllerExcelImportRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/repay/approveStatus", requestType = "get" )
    public String getApproveStatus() {
    return http();
    }


    @Mapping(sub_path = "/repay/download/failFile", requestType = "get" )
    public String downloadFailFile (RepayControllerDownloadFailFileRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/repay/download/template", requestType = "get" )
    public String export() {
    return http();
    }



}



