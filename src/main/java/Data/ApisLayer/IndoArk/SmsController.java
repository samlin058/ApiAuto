package Data.ApisLayer.IndoArk;


import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class SmsController extends ApiBase {

    @Mapping(sub_path = "/sms/send", requestType = "post" ,contentTpye = "application/json")
    public String smsSend() {
    return http();
    }



}



