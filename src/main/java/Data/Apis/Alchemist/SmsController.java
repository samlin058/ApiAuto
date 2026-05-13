package Data.Apis.Alchemist;


import Data.Ro.Alchemist.SmsControllerPostSmsTemplateConfigRo;

import Data.Ro.Alchemist.SmsControllerPreviewRo;

import Data.Ro.Alchemist.SmsControllerPageRo;

import Data.Ro.Alchemist.SmsControllerExportRo;



import Data.Ro.Alchemist.SmsControllerBatchSendMessageRo;



import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class SmsController extends ApiBase {

    @Mapping(sub_path = "/interactions/sms/retrieveAllMessageTemplates", requestType = "get" )
    public String retrieveAllMessageTemplates() {
    return http();
    }


    @Mapping(sub_path = "/interactions/sms/templateConfig", requestType = "post" ,contentTpye = "Application/json")
    public String postSmsTemplateConfig (SmsControllerPostSmsTemplateConfigRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/interactions/sms/preview", requestType = "post" )
    public String preview (SmsControllerPreviewRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/interactions/sms/send/record/page", requestType = "post" ,contentTpye = "Application/json")
    public String page (SmsControllerPageRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/interactions/sms/send/record/export", requestType = "get" )
    public String export (SmsControllerExportRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/interactions/sms/send/resultStatusList", requestType = "get" )
    public String sendResultStatusList() {
    return http();
    }


    @Mapping(sub_path = "/interactions/sms/batchSend", requestType = "post" ,contentTpye = "Application/json")
    public String batchSendMessage (SmsControllerBatchSendMessageRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/interactions/sms/retrieveMessageTemplates/business", requestType = "get" )
    public String retrieveMessageTemplates() {
    return http();
    }



}



