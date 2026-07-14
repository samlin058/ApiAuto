package Data.ApisLayer.BlackShark;
import Data.Ro.BlackShark.AssetJobControllerSaveAutoDisposeJobSameBatchRo;



import Data.Ro.BlackShark.AssetJobControllerSaveSmsSendAutoJobxRo;





import Data.Ro.BlackShark.AssetJobControllerSaveAutoOutboundJobRo;



import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class AssetJobController extends ApiBase {

    @Mapping(sub_path = "/blackShark/assetJob/autoDisposeJob/sameBatch/save", requestType = "post" ,contentTpye = "Application/json")
    public String saveAutoDisposeJobSameBatch (AssetJobControllerSaveAutoDisposeJobSameBatchRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/assetJob/autoJob/strategy/dispose/selected/pkg/list", requestType = "get" )
    public String autoJobSelectedPkgList() {
    return http();
    }


    @Mapping(sub_path = "/blackShark/assetJob/sms/batch/send/save", requestType = "post" ,contentTpye = "Application/json")
    public String saveSmsSendAutoJobx (AssetJobControllerSaveSmsSendAutoJobxRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/assetJob/period/list", requestType = "get" )
    public String autoJobPeriodList() {
    return http();
    }


    @Mapping(sub_path = "/blackShark/assetJob/sms/batch/send/selected/pkgs", requestType = "get" )
    public String smsBatchSendSelectedPkgList() {
    return http();
    }


    @Mapping(sub_path = "/blackShark/assetJob/auto/outbound/save", requestType = "post" ,contentTpye = "Application/json")
    public String saveAutoOutboundJob (AssetJobControllerSaveAutoOutboundJobRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/assetJob/auto/outbound/query", requestType = "get" )
    public String queryAutoOutboundJob() {
    return http();
    }



}



