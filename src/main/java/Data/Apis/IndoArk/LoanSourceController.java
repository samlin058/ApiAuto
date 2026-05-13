package Data.Apis.IndoArk;


import Data.Ro.IndoArk.LoanSourceControllerSourceAddRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class LoanSourceController extends ApiBase {

    @Mapping(sub_path = "/loan/source/list", requestType = "get" )
    public String sourceList() {
    return http();
    }


    @Mapping(sub_path = "/loan/source/add", requestType = "post" ,contentTpye = "application/json")
    public String sourceAdd (LoanSourceControllerSourceAddRo ro) {
    return http(ro);
    }


}



