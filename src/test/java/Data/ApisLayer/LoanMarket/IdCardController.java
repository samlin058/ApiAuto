package Data.ApisLayer.LoanMarket;
import Data.Ro.LoanMarket.IdCardControllerSaveIdCardRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class IdCardController extends ApiBase {

    @Mapping(sub_path = "/id-card/save", requestType = "post" ,contentTpye = "application/json")
    public String saveIdCard (IdCardControllerSaveIdCardRo ro) {
    return http(ro);
    }


}



