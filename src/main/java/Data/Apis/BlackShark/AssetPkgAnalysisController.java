package Data.Apis.BlackShark;
import Data.Ro.BlackShark.AssetPkgAnalysisControllerQueryRo;

import Data.Ro.BlackShark.AssetPkgAnalysisControllerInversionRateListRo;



import Data.Ro.BlackShark.AssetPkgAnalysisControllerAssetPkgTransformLineChartRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class AssetPkgAnalysisController extends ApiBase {

    @Mapping(sub_path = "/blackShark/analysis/statistics/list", requestType = "get" )
    public String query (AssetPkgAnalysisControllerQueryRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/analysis/inversion/rate/list", requestType = "get" )
    public String inversionRateList (AssetPkgAnalysisControllerInversionRateListRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/analysis/dimension/list", requestType = "get" )
    public String getAssetPkgAnalysisDimensionList() {
    return http();
    }


    @Mapping(sub_path = "/blackShark/analysis/transform/lineChart", requestType = "get" )
    public String assetPkgTransformLineChart (AssetPkgAnalysisControllerAssetPkgTransformLineChartRo ro) {
    return http(ro);
    }


}



