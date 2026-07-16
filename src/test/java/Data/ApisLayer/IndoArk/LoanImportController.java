package Data.ApisLayer.IndoArk;




import Data.Ro.IndoArk.LoanImportControllerImportByAppendRo;

import Data.Ro.IndoArk.LoanImportControllerImportByCoverRo;

import Data.Ro.IndoArk.LoanImportControllerLoanImportRo;

import Data.Ro.IndoArk.LoanImportControllerBatchTemplateContentRo;







import Data.Ro.IndoArk.LoanImportControllerImportContactRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class LoanImportController extends ApiBase {

    @Mapping(sub_path = "/loan/import/batch/{importBatchId}/result", requestType = "get" )
    public String getLoanImportStatus() {
    return http();
    }


    @Mapping(sub_path = "/loan/import/batch/{importBatchId}/record/{importRecordId}/fail/download", requestType = "get" )
    public String getLoanImportFailFieldDownload() {
    return http();
    }


    @Mapping(sub_path = "/loan/import/append", requestType = "post" ,contentTpye = "multipart/form-data")
    public String importByAppend (LoanImportControllerImportByAppendRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/loan/import/cover", requestType = "post" ,contentTpye = "multipart/form-data")
    public String importByCover (LoanImportControllerImportByCoverRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/loan/import", requestType = "post" ,contentTpye = "multipart/form-data")
    public String loanImport (LoanImportControllerLoanImportRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/loan/import/batch/template/content", requestType = "get" )
    public String batchTemplateContent (LoanImportControllerBatchTemplateContentRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/loan/import/all/type", requestType = "get" )
    public String getLoanImportAllType() {
    return http();
    }


    @Mapping(sub_path = "/loan/import/{id}/progress", requestType = "get" )
    public String getLoanImportProgress() {
    return http();
    }


    @Mapping(sub_path = "/loan/import/{id}/download/failed", requestType = "get" )
    public String downloadFailFile() {
    return http();
    }


    @Mapping(sub_path = "/loan/import/contact", requestType = "post" ,contentTpye = "multipart/form-data")
    public String importContact (LoanImportControllerImportContactRo ro) {
    return http(ro);
    }


}



