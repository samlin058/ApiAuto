package Data.Apis.BlackShark;


import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class AssetProjectUserConfigController extends ApiBase {

    @Mapping(sub_path = "/blackShark/assetProject/user/projectBd/list", requestType = "get")
    public String userProjectBdList() {
        return http();
    }


    @Mapping(sub_path = "/blackShark/assetProject/user/projectCharge/list", requestType = "get")
    public String userProjectChargeList() {
        return http();
    }


}



