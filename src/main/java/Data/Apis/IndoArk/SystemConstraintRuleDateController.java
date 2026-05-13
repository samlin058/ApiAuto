package Data.Apis.IndoArk;


import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class SystemConstraintRuleDateController extends ApiBase {

    @Mapping(sub_path = "/system/rules", requestType = "post" ,contentTpye = "application/json")
    public String insertRules() {
    return http();
    }



}



