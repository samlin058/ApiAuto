package Data.ApisLayer.IndoArk;
import Data.Ro.IndoArk.MultipleDebtControllerExportMultipleDebtInteractionRo;

import Data.Ro.IndoArk.MultipleDebtControllerGetInteractionsRo;



import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class MultipleDebtController extends ApiBase {

    @Mapping(sub_path = "/multiple/debt/interactions/export", requestType = "get" )
    public String exportMultipleDebtInteraction (MultipleDebtControllerExportMultipleDebtInteractionRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/multiple/debt/interactions", requestType = "get" )
    public String getInteractions (MultipleDebtControllerGetInteractionsRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/multiple/debt/loans/{loanId}", requestType = "get" )
    public String getApplication() {
    return http();
    }



}



