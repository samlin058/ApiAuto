package Data.ApisLayer.BlackShark;

import Data.Ro.BlackShark.PreAssetPkgBatchControllerCheckAssetRo;
import Data.Ro.BlackShark.PreAssetPkgBatchControllerQueryLastRuleConfRo;
import Data.Ro.BlackShark.PreAssetPkgBatchControllerUploadFileRo;
import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class PreAssetPkgBatchController extends ApiBase {

    @Mapping(sub_path = "/blackShark/preAssetPkgBatch/uploadFile", requestType = "post", contentTpye = "multipart/form-data")
    public String uploadFile(PreAssetPkgBatchControllerUploadFileRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/blackShark/preAssetPkgBatch/queryLastRuleConf", requestType = "get")
    public String queryLastRuleConf(PreAssetPkgBatchControllerQueryLastRuleConfRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/blackShark/assetPkg/projectPkgList", requestType = "get")
    public String projectPkgList() {
        return http();
    }

    @Mapping(sub_path = "/blackShark/preAssetPkgBatch/check", requestType = "post", contentTpye = "application/json")
    public String checkAsset(PreAssetPkgBatchControllerCheckAssetRo ro) {
        return http(ro);
    }
}



