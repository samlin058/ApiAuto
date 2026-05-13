package Data.Apis.IndoArk;
import Data.Ro.IndoArk.VoiceApiControllerGetDecryptPhoneByEncryptPhoneRo;





import Data.Ro.IndoArk.VoiceApiControllerCheckoutCallRo;

import Data.Ro.IndoArk.VoiceApiControllerCruiseSelfBatchAppendRo;

import Data.Ro.IndoArk.VoiceApiControllerCruiseDialFavoriteRo;

import Data.Ro.IndoArk.VoiceApiControllerGetCommonPopWindowInfoRo;

import Data.Ro.IndoArk.VoiceApiControllerGetInboundPopWindowInfoRo;
import java.util.List;
import Data.Ro.IndoArk.VoiceApiControllerGetInboundLoanIdRo;
import Data.Ro.IndoArk.VoiceApiControllerAutomaticCruiseCallRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class VoiceApiController extends ApiBase {

    @Mapping(sub_path = "/voice/getDecryptPhone", requestType = "get" )
    public String getDecryptPhoneByEncryptPhone (VoiceApiControllerGetDecryptPhoneByEncryptPhoneRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/voice/type", requestType = "get" )
    public String getContactTag() {
    return http();
    }


    @Mapping(sub_path = "/voice/status", requestType = "get" )
    public String getStatus() {
    return http();
    }


    @Mapping(sub_path = "/voice/call/checkout", requestType = "post" ,contentTpye = "application/json")
    public String checkoutCall (VoiceApiControllerCheckoutCallRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/voice/cruiseDial/selfBatchAppend", requestType = "post" ,contentTpye = "application/json")
    public String cruiseSelfBatchAppend (VoiceApiControllerCruiseSelfBatchAppendRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/voice/cruiseDial/favorite", requestType = "post" ,contentTpye = "application/json")
    public String cruiseDialFavorite (VoiceApiControllerCruiseDialFavoriteRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/voice/common/application", requestType = "get" )
    public String getCommonPopWindowInfo (VoiceApiControllerGetCommonPopWindowInfoRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/voice/inBound/application", requestType = "get" ,contentTpye = "application/json")
    public String getInboundPopWindowInfo(List<VoiceApiControllerGetInboundPopWindowInfoRo> ro) {
    return http(ro);
    }


    @Mapping(sub_path = "/voice/inBound/loan", requestType = "get" ,contentTpye = "application/json")
    public String getInboundLoanId(List<VoiceApiControllerGetInboundLoanIdRo> ro) {
    return http(ro);
    }


    @Mapping(sub_path = "/voice/cruiseDial/checkout", requestType = "post" ,contentTpye = "application/json")
    public String automaticCruiseCall (VoiceApiControllerAutomaticCruiseCallRo ro) {
    return http(ro);
    }


}



