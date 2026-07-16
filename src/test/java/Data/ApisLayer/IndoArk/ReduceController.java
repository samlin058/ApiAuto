package Data.ApisLayer.IndoArk;
import Data.Ro.IndoArk.ReduceControllerQueryByPageRo;

import Data.Ro.IndoArk.ReduceControllerExportRo;

import Data.Ro.IndoArk.ReduceControllerApplyRo;

import Data.Ro.IndoArk.ReduceControllerApproveRo;



import Data.Ro.IndoArk.ReduceControllerGetReduceHistoryRo;

import Data.Ro.IndoArk.ReduceControllerGetLoanInfoRo;







import Data.Ro.IndoArk.ReduceControllerUploadRo;

import Data.Ro.IndoArk.ReduceControllerPreviewRo;

import Data.Ro.IndoArk.ReduceControllerDeleteRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class ReduceController extends ApiBase {

    @Mapping(sub_path = "/reduce/list", requestType = "get" ,contentTpye = "application/json")
    public String queryByPage (ReduceControllerQueryByPageRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/reduce/export", requestType = "get" ,contentTpye = "application/json")
    public String export (ReduceControllerExportRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/reduce/apply", requestType = "post" ,contentTpye = "application/json")
    public String apply (ReduceControllerApplyRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/reduce/approve", requestType = "post" ,contentTpye = "application/json")
    public String approve (ReduceControllerApproveRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/reduce/{id}", requestType = "get" )
    public String getReduceDetail() {
    return http();
    }


    @Mapping(sub_path = "/reduce/history", requestType = "get" )
    public String getReduceHistory (ReduceControllerGetReduceHistoryRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/reduce/loanInfo", requestType = "get" )
    public String getLoanInfo (ReduceControllerGetLoanInfoRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/reduce/contentTypes", requestType = "get" )
    public String getContentTypes() {
    return http();
    }


    @Mapping(sub_path = "/reduce/reasonTypes", requestType = "get" )
    public String getReasonTypes() {
    return http();
    }


    @Mapping(sub_path = "/reduce/approveStatus", requestType = "get" )
    public String getApproveStatus() {
    return http();
    }


    @Mapping(sub_path = "/reduce/upload", requestType = "post" ,contentTpye = "multipart/form-data")
    public String upload (ReduceControllerUploadRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/reduce/preview", requestType = "get" )
    public String preview (ReduceControllerPreviewRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/reduce/attachment/delete", requestType = "post" ,contentTpye = "application/json")
    public String delete (ReduceControllerDeleteRo ro) {
    return http(ro);
    }


}



