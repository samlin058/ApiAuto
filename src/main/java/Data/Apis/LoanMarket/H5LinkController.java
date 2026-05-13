package Data.Apis.LoanMarket;
import Data.Ro.LoanMarket.H5LinkControllerH5Ro;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class H5LinkController extends ApiBase {

    @Mapping(sub_path = "/link/h5", requestType = "get" )
    public String h5 (H5LinkControllerH5Ro ro) {
    return http(ro);
    }


}



