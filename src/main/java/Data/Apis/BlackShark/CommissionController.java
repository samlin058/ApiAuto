package Data.Apis.BlackShark;
import Data.Ro.BlackShark.CommissionControllerDetailListRo;



import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class CommissionController extends ApiBase {

    @Mapping(sub_path = "/commission/detail/list", requestType = "get" )
    public String detailList (CommissionControllerDetailListRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/commission/query", requestType = "get" )
    public String query() {
    return http();
    }



}



