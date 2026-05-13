package Data.Apis.IndoArk;


import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class AgentGroupController extends ApiBase {

    @Mapping(sub_path = "/agent-group/subordinate", requestType = "get" )
    public String getSubordinateAgentGroup() {
    return http();
    }



}



