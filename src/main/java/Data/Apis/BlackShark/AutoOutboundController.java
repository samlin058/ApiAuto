package Data.Apis.BlackShark;




import Data.Ro.BlackShark.AutoOutboundControllerPageListRo;

import Data.Ro.BlackShark.AutoOutboundControllerDownloadRo;



import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class AutoOutboundController extends ApiBase {

    @Mapping(sub_path = "/blackShark/autoOutbound/autoOutboundList", requestType = "get" )
    public String autoOutboundList() {
    return http();
    }


    @Mapping(sub_path = "/blackShark/autoOutbound/autoOutboundBatchStatusList", requestType = "get" )
    public String autoOutboundBatchStatusList() {
    return http();
    }


    @Mapping(sub_path = "/blackShark/autoOutbound/pageList", requestType = "get" )
    public String pageList (AutoOutboundControllerPageListRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/autoOutbound/download", requestType = "get" )
    public String download (AutoOutboundControllerDownloadRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/autoOutbound/cancel/{batchNo}", requestType = "post" )
    public String cancel() {
    return http();
    }



}



