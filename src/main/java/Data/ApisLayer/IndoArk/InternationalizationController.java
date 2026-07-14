package Data.ApisLayer.IndoArk;


import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class InternationalizationController extends ApiBase {

    @Mapping(sub_path = "/internationalization/system/config", requestType = "get" )
    public String GetSystemConfig() {
    return http();
    }



}



