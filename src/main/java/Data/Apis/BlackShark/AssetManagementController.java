package Data.Apis.BlackShark;


import Data.Ro.BlackShark.AssetManagementControllerQueryStopCollectListRo;

import Data.Ro.BlackShark.AssetManagementControllerStopCollectRo;

import Data.Ro.BlackShark.AssetManagementControllerDownloadFailFileRo;

import Data.Ro.BlackShark.AssetManagementControllerStopCollectStatisticRo;

import Data.Ro.BlackShark.AssetManagementControllerImportStopCollectRo;

import Data.Ro.BlackShark.AssetManagementControllerQueryListRo;







import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class AssetManagementController extends ApiBase {

    @Mapping(sub_path = "/blackShark/asset/export/suspend", requestType = "get" )
    public String exportByStatus() {
    return http();
    }


    @Mapping(sub_path = "/blackShark/asset/query/stopUrging/result", requestType = "get" )
    public String queryStopCollectList (AssetManagementControllerQueryStopCollectListRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/asset/result/stopCollect", requestType = "post" )
    public String stopCollect (AssetManagementControllerStopCollectRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/asset/downloadFailFile", requestType = "get" )
    public String downloadFailFile (AssetManagementControllerDownloadFailFileRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/asset/import/stopCollectStatistic", requestType = "get" )
    public String stopCollectStatistic (AssetManagementControllerStopCollectStatisticRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/asset/import/stopCollect", requestType = "post" )
    public String importStopCollect (AssetManagementControllerImportStopCollectRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/asset/query", requestType = "post" ,contentTpye = "Application/json")
    public String queryList (AssetManagementControllerQueryListRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/asset/exportAll", requestType = "get" )
    public String exportAll() {
    return http();
    }


    @Mapping(sub_path = "/blackShark/asset/queryTotal", requestType = "get" )
    public String queryTotal() {
    return http();
    }


    @Mapping(sub_path = "/blackShark/asset/downloadPrejudge", requestType = "get" )
    public String downloadPrejudge() {
    return http();
    }



}



