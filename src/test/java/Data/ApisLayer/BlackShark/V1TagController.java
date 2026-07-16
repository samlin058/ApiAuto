package Data.ApisLayer.BlackShark;
import Data.Ro.BlackShark.V1TagControllerDownloadRo;

import Data.Ro.BlackShark.V1TagControllerUploadRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class V1TagController extends ApiBase {

    @Mapping(sub_path = "/api/v1/tags/{parentTagCode}/download", requestType = "get" )
    public String download (V1TagControllerDownloadRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/api/v1/tags/{parentTagCode}", requestType = "post" ,contentTpye = "multipart/form-data")
    public String upload (V1TagControllerUploadRo ro) {
    return http(ro);
    }


}



