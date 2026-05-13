package Data.Apis.IndoArk;


import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class SmsController extends ApiBase {

    @Mapping(sub_path = "/sms/send", requestType = "post" ,contentTpye = "application/json")
    public String smsSend() {
    return http();
    }



}



