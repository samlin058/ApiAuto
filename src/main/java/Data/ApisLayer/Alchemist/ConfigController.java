package Data.ApisLayer.Alchemist;
import Data.Ro.Alchemist.ConfigControllerLeadConfigRo;



import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class ConfigController extends ApiBase {

    @Mapping(sub_path = "/config/lead", requestType = "post" ,contentTpye = "Application/json")
    public String leadConfig (ConfigControllerLeadConfigRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/config/lead/query", requestType = "get" )
    public String queryLead() {
    return http();
    }



}



