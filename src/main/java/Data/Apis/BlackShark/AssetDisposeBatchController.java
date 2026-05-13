package Data.Apis.BlackShark;

import Data.Ro.BlackShark.*;


import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class AssetDisposeBatchController extends ApiBase {

    @Mapping(sub_path = "/blackShark/asset/batch/importAssignment", requestType = "post", contentTpye = "multipart/form-data")
    public String importAssignment(AssetDisposeBatchControllerImportAssignmentRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/blackShark/asset/batch/importStatistic", requestType = "get")
    public String getImportStatistic(AssetDisposeBatchControllerGetImportStatisticRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/blackShark/asset/batch/didrk", requestType = "get")
    public String didrk(AssetDisposeBatchControllerDidrkRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/blackShark/asset/batch/export", requestType = "get")
    public String exportAsset() {
        return http();
    }

    @Mapping(sub_path = "/blackShark/asset/batch/assignAndPush", requestType = "post", contentTpye = "Application/json")
    public String assignAndPush(AssetDisposeBatchControllerAssignAndPushRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/blackShark/asset/batch/assetQuery", requestType = "post")
    public String assetQuery() {
        return http();
    }

    @Mapping(sub_path = "/blackShark/asset/batch/stat", requestType = "get")
    public String batchStat(AssetDisposeBatchControllerBatchStatRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/blackShark/asset/batch/query", requestType = "post", contentTpye = "Application/json")
    public String query(AssetDisposeBatchControllerQueryRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/blackShark/asset/batch/assignment", requestType = "post", contentTpye = "application/json")
    public String assignment(AssetDisposeBatchControllerAssignmentRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/blackShark/asset/batch/disposer/push", requestType = "post", contentTpye = "application/json")
    public String disposerPush(AssetDisposeBatchControllerDisposerPushRo ro) {
        return http(ro);
    }

}



