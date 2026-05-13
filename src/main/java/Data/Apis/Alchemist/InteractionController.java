package Data.Apis.Alchemist;
import Data.Ro.Alchemist.InteractionControllerOperateRo;

import Data.Ro.Alchemist.InteractionControllerAiCallRecordRo;



import Data.Ro.Alchemist.InteractionControllerBatchOperatorRo;

import Data.Ro.Alchemist.InteractionControllerAiChatContentRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class InteractionController extends ApiBase {

    @Mapping(sub_path = "/interactions/operate", requestType = "post" ,contentTpye = "Application/json")
    public String operate (InteractionControllerOperateRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/interactions/aiCallRecord", requestType = "post" ,contentTpye = "Application/json")
    public String aiCallRecord (InteractionControllerAiCallRecordRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/interactions/pushAi/{billId}", requestType = "post" )
    public String pushAi() {
    return http();
    }


    @Mapping(sub_path = "/interactions/batchOperate", requestType = "post" ,contentTpye = "Application/json")
    public String batchOperator (InteractionControllerBatchOperatorRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/interactions/ai/chat/content", requestType = "get" )
    public String aiChatContent (InteractionControllerAiChatContentRo ro) {
    return http(ro);
    }


}



