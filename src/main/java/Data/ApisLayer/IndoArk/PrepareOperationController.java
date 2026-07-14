package Data.ApisLayer.IndoArk;
import Data.Ro.IndoArk.PrepareOperationControllerRecordRepaySaveRo;

import Data.Ro.IndoArk.PrepareOperationControllerRecordPagesRo;

import Data.Ro.IndoArk.PrepareOperationControllerRecordDeleteRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class PrepareOperationController extends ApiBase {

    @Mapping(sub_path = "/pre/record/repay/save", requestType = "post" ,contentTpye = "application/json")
    public String recordRepaySave (PrepareOperationControllerRecordRepaySaveRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/pre/record/pages/{loanId}", requestType = "get" )
    public String recordPages (PrepareOperationControllerRecordPagesRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/pre/record/delete", requestType = "get" )
    public String recordDelete (PrepareOperationControllerRecordDeleteRo ro) {
    return http(ro);
    }


}



