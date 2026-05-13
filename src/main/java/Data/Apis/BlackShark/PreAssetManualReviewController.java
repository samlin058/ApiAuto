package Data.Apis.BlackShark;

import Data.Ro.BlackShark.PreAssetManualReviewControllerQueryBatchListRo;
import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class PreAssetManualReviewController extends ApiBase {

    @Mapping(sub_path = "/blackShark/assertImport/manualReview/queryBatchList", requestType = "post", contentTpye = "application/json")
    public String queryBatchList(PreAssetManualReviewControllerQueryBatchListRo ro) {
        return http(ro);
    }


}



