package Data.Apis.LoanMarket;

import Data.Ro.LoanMarket.BackDoorContollerEncryptRo;

import Data.Ro.LoanMarket.BackDoorContollerUpdateLoanRo;
import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class BackDoorContoller extends ApiBase {

    @Mapping(sub_path = "/back-door/encrypt", requestType = "get")
    public String encrypt(BackDoorContollerEncryptRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/back-door/update-loan", requestType = "post", contentTpye = "application/json")
    public String updateLoan(BackDoorContollerUpdateLoanRo ro) {
        return http(ro);
    }
}



