package Data.ApisLayer.IndoArk;




import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class AgentController extends ApiBase {

    @Mapping(sub_path = "/agent/all/active", requestType = "get" )
    public String listAllActive() {
    return http();
    }


    @Mapping(sub_path = "/agent/subordinate", requestType = "get" )
    public String getSubordinateAgents() {
    return http();
    }



}



