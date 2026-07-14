package Data.ApisLayer.IndoArk;






import Data.Ro.IndoArk.LoanInfoControllerRmRecentlyOpenedCaseRo;





import Data.Ro.IndoArk.LoanInfoControllerAddColorTagRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class LoanInfoController extends ApiBase {

    @Mapping(sub_path = "/loan/dispose/loans/{id}/application", requestType = "get" )
    public String getApplication() {
    return http();
    }


    @Mapping(sub_path = "/loan/dispose/recentlyOpenedCases", requestType = "get" )
    public String getRecentlyOpenedCases() {
    return http();
    }


    @Mapping(sub_path = "/loan/dispose/{id}/appDetailParameter", requestType = "get" )
    public String retrieveDetail() {
    return http();
    }


    @Mapping(sub_path = "/loan/dispose/removeROC", requestType = "get" )
    public String rmRecentlyOpenedCase (LoanInfoControllerRmRecentlyOpenedCaseRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/loan/dispose/{id}/idNo", requestType = "get" )
    public String getCustomerIdnoWithoutMask() {
    return http();
    }


    @Mapping(sub_path = "/loan/dispose/{id}/censusRegisterAddress", requestType = "get" )
    public String getCensusRegisterAddressWithoutMask() {
    return http();
    }


    @Mapping(sub_path = "/loan/dispose/color", requestType = "post" ,contentTpye = "application/json")
    public String addColorTag (LoanInfoControllerAddColorTagRo ro) {
    return http(ro);
    }


}



