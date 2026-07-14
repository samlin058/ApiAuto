package Data.ApisLayer.IndoArk;


import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class SmsChannelController extends ApiBase {

    @Mapping(sub_path = "/sms/channels", requestType = "get" )
    public String getChannels() {
    return http();
    }



}



