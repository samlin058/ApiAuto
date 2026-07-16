package Data.ApisLayer.Alchemist;

import Data.Ro.Alchemist.getApplicationStatuesRo;
import Data.Ro.Alchemist.LeadControllerCreateShortLinkRo;
import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class LeadController extends ApiBase {

    @Mapping(sub_path = "/leads/{leadId}/detail", requestType = "get")
    public String getApplicationStatues(getApplicationStatuesRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/leads/createShortLink", requestType = "post", contentTpye = "Application/json")
    public String createShortLink(LeadControllerCreateShortLinkRo ro) {
        return http(ro);
    }
}
