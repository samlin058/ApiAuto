package Data.ApisLayer.BlackShark;


import Data.Ro.BlackShark.AssetCollectionRecordsControllerGetCollectionRecordsRo;

import Data.Ro.BlackShark.AssetCollectionRecordsControllerDownloadRecordingFilesRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class AssetCollectionRecordsController extends ApiBase {

    @Mapping(sub_path = "/collection/get", requestType = "get" )
    public String getCollectionRecordList() {
    return http();
    }


    @Mapping(sub_path = "/collection/records/get", requestType = "get" )
    public String getCollectionRecords (AssetCollectionRecordsControllerGetCollectionRecordsRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/collection/records/recordingFiles/download", requestType = "get" ,contentTpye = "Application/json")
    public String downloadRecordingFiles (AssetCollectionRecordsControllerDownloadRecordingFilesRo ro) {
    return http(ro);
    }


}



