package Data.ApisLayer.BlackShark;


import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class LinkupController extends ApiBase {

    @Mapping(sub_path = "/blackShark/open/linkup/callback", requestType = "post" )
    public String callback() {
    return http();
    }



}



