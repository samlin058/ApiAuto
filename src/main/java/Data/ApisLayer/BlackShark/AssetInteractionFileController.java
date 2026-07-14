package Data.ApisLayer.BlackShark;
import Data.Ro.BlackShark.AssetInteractionFileControllerImportCallRecordRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class AssetInteractionFileController extends ApiBase {

    @Mapping(sub_path = "/interaction/file/importCallRecord", requestType = "post" ,contentTpye = "multipart/form-data")
    public String importCallRecord (AssetInteractionFileControllerImportCallRecordRo ro) {
    return http(ro);
    }


}



