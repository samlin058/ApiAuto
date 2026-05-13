package Data.Apis.IndoArk;
import Data.Ro.IndoArk.SmsRecordControllerSmsSendRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class SmsRecordController extends ApiBase {

    @Mapping(sub_path = "/sms/record/page", requestType = "get" )
    public String smsSend (SmsRecordControllerSmsSendRo ro) {
    return http(ro);
    }


}



