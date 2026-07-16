package Data.ApisLayer.BlackShark;
import Data.Ro.BlackShark.QualityInspectionControllerGetDisposerTotalRo;



import Data.Ro.BlackShark.QualityInspectionControllerGetTotalInfoRo;

import Data.Ro.BlackShark.QualityInspectionControllerGetAssetPkgTotalRo;

import Data.Ro.BlackShark.QualityInspectionControllerGetQualityInspectionListRo;

import Data.Ro.BlackShark.QualityInspectionControllerResultRo;

import Data.Ro.BlackShark.QualityInspectionControllerDeleteSensitiveWordsRo;

import Data.Ro.BlackShark.QualityInspectionControllerUpdateSensitiveWordsRo;





import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class QualityInspectionController extends ApiBase {

    @Mapping(sub_path = "/quality-inspection/getDisposerTotal", requestType = "get" )
    public String getDisposerTotal (QualityInspectionControllerGetDisposerTotalRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/quality-inspection/sensitive-words/items", requestType = "get" )
    public String sensitiveWordsList() {
    return http();
    }


    @Mapping(sub_path = "/quality-inspection/getTotalInfo", requestType = "get" )
    public String getTotalInfo (QualityInspectionControllerGetTotalInfoRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/quality-inspection/getAssetPkgTotal", requestType = "get" )
    public String getAssetPkgTotal (QualityInspectionControllerGetAssetPkgTotalRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/quality-inspection/getList", requestType = "get" )
    public String getQualityInspectionList (QualityInspectionControllerGetQualityInspectionListRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/quality-inspection/result", requestType = "post" ,contentTpye = "Application/json")
    public String result (QualityInspectionControllerResultRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/quality-inspection/sensitive-words/delete", requestType = "post" ,contentTpye = "Application/json")
    public String deleteSensitiveWords (QualityInspectionControllerDeleteSensitiveWordsRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/quality-inspection/sensitive-words/update", requestType = "post" ,contentTpye = "Application/json")
    public String updateSensitiveWords (QualityInspectionControllerUpdateSensitiveWordsRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/quality-inspection/dial-record/inspect/{connId}", requestType = "get" )
    public String getInspect() {
    return http();
    }


    @Mapping(sub_path = "/quality-inspection/sensitive-words/add", requestType = "post" ,contentTpye = "Application/json")
    public String addSensitiveWords() {
    return http();
    }



}



