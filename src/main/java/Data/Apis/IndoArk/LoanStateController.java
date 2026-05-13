package Data.Apis.IndoArk;




import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class LoanStateController extends ApiBase {

    @Mapping(sub_path = "/loan/{loanId}/state", requestType = "get" ,contentTpye = "application/json")
    public String getState() {
    return http();
    }


    @Mapping(sub_path = "/loan/state/enumList", requestType = "get" )
    public String stateEnumList() {
    return http();
    }



}



