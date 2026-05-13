package Data.Apis.IndoArk;




import Data.Ro.IndoArk.CustomerTagControllerDeleteCustomerTagRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class CustomerTagController extends ApiBase {

    @Mapping(sub_path = "/loan/dispose/tags", requestType = "get" )
    public String getCustomerTags() {
    return http();
    }


    @Mapping(sub_path = "/loan/dispose/{id}/tags", requestType = "get" ,contentTpye = "application/json")
    public String insertCustomerTag() {
    return http();
    }


    @Mapping(sub_path = "/loan/dispose/{id}/tags/{customerTagId}/delete", requestType = "post" ,contentTpye = "application/json")
    public String deleteCustomerTag (CustomerTagControllerDeleteCustomerTagRo ro) {
    return http(ro);
    }


}



