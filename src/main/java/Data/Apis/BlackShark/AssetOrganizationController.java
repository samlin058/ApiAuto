package Data.Apis.BlackShark;


import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class AssetOrganizationController extends ApiBase {

    @Mapping(sub_path = "/blackShark/assetOrganization/listEnable", requestType = "get")
    public String listEnable() {
        return http();
    }


}



