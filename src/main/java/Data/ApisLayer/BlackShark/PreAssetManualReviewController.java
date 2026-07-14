package Data.ApisLayer.BlackShark;

import Data.Ro.BlackShark.PreAssetManualReviewControllerQueryBatchListRo;
import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class PreAssetManualReviewController extends ApiBase {

    @Mapping(sub_path = "/blackShark/assertImport/manualReview/queryBatchList", requestType = "post", contentTpye = "application/json")
    public String queryBatchList(PreAssetManualReviewControllerQueryBatchListRo ro) {
        return http(ro);
    }


}



