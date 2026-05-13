package Data.Apis.IndoArk;








import Data.Ro.IndoArk.InteractionControllerGetInteractionsRo;

import Data.Ro.IndoArk.InteractionControllerExportMultipleDebtInteractionRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class InteractionController extends ApiBase {

    @Mapping(sub_path = "/loan/dispose/conclusion/tree", requestType = "get" )
    public String getConclusionTree() {
    return http();
    }


    @Mapping(sub_path = "/loan/dispose/types", requestType = "get" )
    public String getInteractionTypes() {
    return http();
    }


    @Mapping(sub_path = "/loan/dispose/actions", requestType = "get" )
    public String getInteractionSubTypes() {
    return http();
    }


    @Mapping(sub_path = "/loan/dispose/conclusions", requestType = "get" )
    public String getInteractionConclusions() {
    return http();
    }


    @Mapping(sub_path = "/loan/dispose/interactions", requestType = "get" )
    public String getInteractions (InteractionControllerGetInteractionsRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/loan/dispose/interactions/export", requestType = "get" )
    public String exportMultipleDebtInteraction (InteractionControllerExportMultipleDebtInteractionRo ro) {
    return http(ro);
    }


}



