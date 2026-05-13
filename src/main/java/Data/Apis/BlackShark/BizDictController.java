package Data.Apis.BlackShark;

import Data.Ro.BlackShark.BizDictControllerQueryRo;
import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class BizDictController extends ApiBase {

    @Mapping(sub_path = "/blackShark/bizDict/query", requestType = "get")
    public String query(BizDictControllerQueryRo ro) {
        return http(ro);
    }

}



