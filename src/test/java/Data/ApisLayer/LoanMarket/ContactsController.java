package Data.ApisLayer.LoanMarket;

import Data.Ro.LoanMarket.ContactsControllerSaveContactsRo;
import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;

import java.util.List;


public class ContactsController extends ApiBase {

    @Mapping(sub_path = "/contacts/save", requestType = "post", contentTpye = "application/json")
    public String saveContacts(List<ContactsControllerSaveContactsRo> ro) {
        return http(ro);
    }


}



