package Data.ApisLayer.IndoArk;
import Data.Ro.IndoArk.AgentVoiceControllerAgentVoicesRo;







import Data.Ro.IndoArk.AgentVoiceControllerExportRo;

import Data.Ro.IndoArk.AgentVoiceControllerPollAgentVoiceDataRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class AgentVoiceController extends ApiBase {

    @Mapping(sub_path = "/report/agent/voice/list", requestType = "get" )
    public String agentVoices (AgentVoiceControllerAgentVoicesRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/report/agent/voice/latest/dataTime", requestType = "get" )
    public String latestDataTime() {
    return http();
    }


    @Mapping(sub_path = "/report/agent/voice/agent/enums", requestType = "get" )
    public String agentEnums() {
    return http();
    }


    @Mapping(sub_path = "/report/agent/voice/agentGroup/enums", requestType = "get" )
    public String agentGroupEnums() {
    return http();
    }


    @Mapping(sub_path = "/report/agent/voice/export", requestType = "get" )
    public String export (AgentVoiceControllerExportRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/report/agent/voice/pull/task", requestType = "get" )
    public String pollAgentVoiceData (AgentVoiceControllerPollAgentVoiceDataRo ro) {
    return http(ro);
    }


}



