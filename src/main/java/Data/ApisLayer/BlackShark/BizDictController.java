package Data.ApisLayer.BlackShark;

import Data.Ro.BlackShark.BizDictControllerQueryRo;
import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class BizDictController extends ApiBase {

    @Mapping(sub_path = "/blackShark/bizDict/query", requestType = "get")
    public String query(BizDictControllerQueryRo ro) {
        return http(ro);
    }

}



