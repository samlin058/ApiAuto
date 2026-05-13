package Data.Apis.BlackShark;


import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class LinkupController extends ApiBase {

    @Mapping(sub_path = "/blackShark/open/linkup/callback", requestType = "post" )
    public String callback() {
    return http();
    }



}



