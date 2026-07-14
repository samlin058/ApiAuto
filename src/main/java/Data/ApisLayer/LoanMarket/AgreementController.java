package Data.ApisLayer.LoanMarket;

import Data.Ro.LoanMarket.AgreementControllerGetRo;
import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class AgreementController extends ApiBase {



    @Mapping(sub_path = "/agreement/list", requestType = "get")
    public String get(AgreementControllerGetRo ro) {
        return http(ro);
    }



}



