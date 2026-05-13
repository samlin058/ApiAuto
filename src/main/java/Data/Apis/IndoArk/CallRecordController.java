package Data.Apis.IndoArk;
import Data.Ro.IndoArk.CallRecordControllerInitCallRecordRo;

import Data.Ro.IndoArk.CallRecordControllerUpdateCallRecordRo;

import Data.Ro.IndoArk.CallRecordControllerGetCallRecordsRo;

import Data.Ro.IndoArk.CallRecordControllerLinkupCallRecordCallbackRo;



import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class CallRecordController extends ApiBase {

    @Mapping(sub_path = "/call/callRecord", requestType = "post" ,contentTpye = "application/json")
    public String initCallRecord (CallRecordControllerInitCallRecordRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/call/callRecord/{callRecordId}/update", requestType = "post" ,contentTpye = "application/json")
    public String updateCallRecord (CallRecordControllerUpdateCallRecordRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/call/callRecords", requestType = "get" )
    public String getCallRecords (CallRecordControllerGetCallRecordsRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/call/callRecord/callback", requestType = "post" ,contentTpye = "application/json")
    public String linkupCallRecordCallback (CallRecordControllerLinkupCallRecordCallbackRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/call/callRecord/loan/{loanId}/today", requestType = "get" )
    public String getLoanTodayCallRecord() {
    return http();
    }



}



