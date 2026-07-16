package Data.ApisLayer.BlackShark;
import Data.Ro.BlackShark.AssetDisposeJobControllerQuerySubStrategyExecuteResultRo;
import java.util.List;
import Data.Ro.BlackShark.AssetDisposeJobControllerQueryResultRo;

import Data.Ro.BlackShark.AssetDisposeJobControllerStopSimulateJobRo;

import Data.Ro.BlackShark.AssetDisposeJobControllerRetryRo;

import Data.Ro.BlackShark.AssetDisposeJobControllerListByPageRo;

import Data.Ro.BlackShark.AssetDisposeJobControllerListenRo;

import Data.Ro.BlackShark.AssetDisposeJobControllerDownloadJobResultRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class AssetDisposeJobController extends ApiBase {

    @Mapping(sub_path = "/blackShark/dispose/job/querySubStrategyExecuteResult", requestType = "post" ,contentTpye = "Application/json")
    public String querySubStrategyExecuteResult(List<AssetDisposeJobControllerQuerySubStrategyExecuteResultRo> ro) {
    return http(ro);
    }


    @Mapping(sub_path = "/blackShark/dispose/job/query-result", requestType = "get" )
    public String queryResult (AssetDisposeJobControllerQueryResultRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/dispose/job/stopSimulateJob", requestType = "post" ,contentTpye = "multipart/form-data")
    public String stopSimulateJob (AssetDisposeJobControllerStopSimulateJobRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/dispose/job/retry", requestType = "post" )
    public String retry (AssetDisposeJobControllerRetryRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/dispose/job/list-by-page", requestType = "get" )
    public String listByPage (AssetDisposeJobControllerListByPageRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/dispose/job/listenJob", requestType = "get" )
    public String listen (AssetDisposeJobControllerListenRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/dispose/job/download-job-result", requestType = "get" )
    public String downloadJobResult (AssetDisposeJobControllerDownloadJobResultRo ro) {
    return http(ro);
    }


}



