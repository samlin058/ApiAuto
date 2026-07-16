package Data.ApisLayer.IndoArk;






import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class SmsTemplateController extends ApiBase {

    @Mapping(sub_path = "/sms/templates", requestType = "post" ,contentTpye = "application/json")
    public String insertTemplate() {
    return http();
    }


    @Mapping(sub_path = "/sms/templates/{id}", requestType = "delete" ,contentTpye = "application/json")
    public String deleteTemplate() {
    return http();
    }


    @Mapping(sub_path = "/sms/templates/variables", requestType = "get" )
    public String getVariables() {
    return http();
    }



}



