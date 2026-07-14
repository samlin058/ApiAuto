package Data.ApisLayer.IndoArk;


import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class SystemConstraintRuleDateController extends ApiBase {

    @Mapping(sub_path = "/system/rules", requestType = "post" ,contentTpye = "application/json")
    public String insertRules() {
    return http();
    }



}



