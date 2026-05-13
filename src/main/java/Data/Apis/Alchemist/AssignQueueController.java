package Data.Apis.Alchemist;

import Data.Ro.Alchemist.AssignQueueControllerGetAllPropertiesByQueueIdRo;


import Data.Ro.Alchemist.AssignQueueControllerSaveAgentSkillRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class AssignQueueController extends ApiBase {

    @Mapping(sub_path = "/assignQueues/properties", requestType = "get")
    public String getAllPropertiesByQueueId(AssignQueueControllerGetAllPropertiesByQueueIdRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/assignQueues/processors", requestType = "get")
    public String getQueueProcessors() {
        return http();
    }

    @Mapping(sub_path = "/assignQueues/agents/save", requestType = "post", contentTpye = "Application/json")
    public String saveAgentSkill(AssignQueueControllerSaveAgentSkillRo ro) {
        return http(ro);
    }


}



