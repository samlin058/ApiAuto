package Data.ApisLayer.IndoArk;


import Data.Ro.IndoArk.ResourceControllerQueryRo;

import Data.Ro.IndoArk.ResourceControllerQueryOneRo;

import Data.Ro.IndoArk.ResourceControllerSaveRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class ResourceController extends ApiBase {

    @Mapping(sub_path = "/resource/all", requestType = "post" )
    public String getAllResources() {
    return http();
    }


    @Mapping(sub_path = "/resource/query", requestType = "get" )
    public String query (ResourceControllerQueryRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/resource/queryOne", requestType = "get" )
    public String queryOne (ResourceControllerQueryOneRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/resource/save", requestType = "post" ,contentTpye = "application/json")
    public String save (ResourceControllerSaveRo ro) {
    return http(ro);
    }


}



