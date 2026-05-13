package Data.Apis.IndoArk;


import Data.Ro.IndoArk.SmsBatchControllerRecordPageRo;

import Data.Ro.IndoArk.SmsBatchControllerSmsBatchSendRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class SmsBatchController extends ApiBase {

    @Mapping(sub_path = "/sms/batch/record/status/enums", requestType = "get" )
    public String batchRecordStatusEnums() {
    return http();
    }


    @Mapping(sub_path = "/sms/batch/record/page", requestType = "get" )
    public String recordPage (SmsBatchControllerRecordPageRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/sms/batch/send", requestType = "post" ,contentTpye = "application/json")
    public String smsBatchSend (SmsBatchControllerSmsBatchSendRo ro) {
    return http(ro);
    }


}



