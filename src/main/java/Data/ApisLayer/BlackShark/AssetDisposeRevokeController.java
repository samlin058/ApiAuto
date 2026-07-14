package Data.ApisLayer.BlackShark;




import Data.Ro.BlackShark.AssetDisposeRevokeControllerDisposerRevokeRo;
import Data.Ro.BlackShark.AssetDisposeRevokeControllerSaveAssetAutoRevokeJobRo;



import Data.Ro.BlackShark.AssetDisposeRevokeControllerBatchRevokeRo;

import Data.Ro.BlackShark.AssetDisposeRevokeControllerAgainRevokeRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class AssetDisposeRevokeController extends ApiBase {

    @Mapping(sub_path = "/blackShark/asset/project/pkg/list", requestType = "get" )
    public String projectPkgList() {
    return http();
    }


    @Mapping(sub_path = "/blackShark/asset/disposer/revoke/job", requestType = "get" )
    public String queryRevokeJob() {
    return http();
    }


    @Mapping(sub_path = "/blackShark/asset/disposer/revoke/autojob/asset/save", requestType = "post" ,contentTpye = "Application/json")
    public String saveAssetAutoRevokeJob (AssetDisposeRevokeControllerSaveAssetAutoRevokeJobRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/asset/autoJob/revoke/selected/pkg/list", requestType = "get" )
    public String autoJobSelectedPkgList() {
    return http();
    }


    @Mapping(sub_path = "/blackShark/asset/batch/revoke", requestType = "get" )
    public String batchRevoke (AssetDisposeRevokeControllerBatchRevokeRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/asset/again/revoke", requestType = "get" )
    public String againRevoke (AssetDisposeRevokeControllerAgainRevokeRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/asset/disposer/revoke", requestType = "post" ,contentTpye = "application/json")
    public String disposerRevoke (AssetDisposeRevokeControllerDisposerRevokeRo ro) {
        return http(ro);
    }

}



