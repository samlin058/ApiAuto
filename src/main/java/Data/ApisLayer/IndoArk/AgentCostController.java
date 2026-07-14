package Data.ApisLayer.IndoArk;
import Data.Ro.IndoArk.AgentCostControllerGetAgentCostRo;

import Data.Ro.IndoArk.AgentCostControllerExportRo;

import Data.Ro.IndoArk.AgentCostControllerUpdateAgentCostRo;

import Data.Ro.IndoArk.AgentCostControllerBatchUpdateAgentCostRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class AgentCostController extends ApiBase {

    @Mapping(sub_path = "/agents/cost", requestType = "get" )
    public String getAgentCost (AgentCostControllerGetAgentCostRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/agents/cost/export", requestType = "get" )
    public String export (AgentCostControllerExportRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/agents/cost/{id}", requestType = "post" ,contentTpye = "application/json")
    public String updateAgentCost (AgentCostControllerUpdateAgentCostRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/agents/cost/batch", requestType = "post" ,contentTpye = "application/json")
    public String batchUpdateAgentCost (AgentCostControllerBatchUpdateAgentCostRo ro) {
    return http(ro);
    }


}



