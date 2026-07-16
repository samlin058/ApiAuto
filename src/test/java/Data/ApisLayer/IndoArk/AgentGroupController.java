package Data.ApisLayer.IndoArk;


import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class AgentGroupController extends ApiBase {

    @Mapping(sub_path = "/agent-group/subordinate", requestType = "get" )
    public String getSubordinateAgentGroup() {
    return http();
    }



}



