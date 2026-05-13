package Data.Apis.LoanMarket;

import Data.Ro.LoanMarket.EnumListControllerTypesRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class EnumListController extends ApiBase {

    @Mapping(sub_path = "/enum/list", requestType = "get")
    public String types(EnumListControllerTypesRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/enum/china-area", requestType = "get")
    public String chinaArea() {
        return http();
    }

}



