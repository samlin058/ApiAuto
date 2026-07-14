package Data.ApisLayer.IndoArk;
import Data.Ro.IndoArk.OperationLogControllerApplyListRo;



import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class OperationLogController extends ApiBase {

    @Mapping(sub_path = "/operationLog/list", requestType = "get" ,contentTpye = "application/json")
    public String applyList (OperationLogControllerApplyListRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/operationLog/type/list", requestType = "get" )
    public String typeList() {
    return http();
    }



}



