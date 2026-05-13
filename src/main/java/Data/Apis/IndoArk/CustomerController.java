package Data.Apis.IndoArk;


import Data.Ro.IndoArk.CustomerControllerInsertCustomerContactRo;

import Data.Ro.IndoArk.CustomerControllerFavoriteRo;

import Data.Ro.IndoArk.CustomerControllerCancelFavoriteRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class CustomerController extends ApiBase {

    @Mapping(sub_path = "/customers/{id}/contacts", requestType = "get" )
    public String getContacts() {
    return http();
    }


    @Mapping(sub_path = "/customers/{id}/hot/contacts", requestType = "post" ,contentTpye = "application/json")
    public String insertCustomerContact (CustomerControllerInsertCustomerContactRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/customers/contacts/favorite", requestType = "post" ,contentTpye = "application/json")
    public String favorite (CustomerControllerFavoriteRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/customers/contacts/favorite/cancel", requestType = "post" ,contentTpye = "application/json")
    public String cancelFavorite (CustomerControllerCancelFavoriteRo ro) {
    return http(ro);
    }


}



