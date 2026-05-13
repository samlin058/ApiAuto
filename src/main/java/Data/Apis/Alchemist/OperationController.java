package Data.Apis.Alchemist;


import Data.Ro.Alchemist.OperationControllerImportOperationRo;

import Data.Ro.Alchemist.OperationControllerPageRo;



import Data.Ro.Alchemist.OperationControllerExportRo;



import Data.Ro.Alchemist.OperationControllerCrackRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class OperationController extends ApiBase {

    @Mapping(sub_path = "/operation/status/list", requestType = "get" )
    public String getStatusList() {
    return http();
    }


    @Mapping(sub_path = "/operation/files/import", requestType = "post" ,contentTpye = "multipart/form-data")
    public String importOperation (OperationControllerImportOperationRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/operation/page", requestType = "post" ,contentTpye = "Application/json")
    public String page (OperationControllerPageRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/operation/files/template", requestType = "get" )
    public String downloadFile() {
    return http();
    }


    @Mapping(sub_path = "/operation/export", requestType = "get" )
    public String export (OperationControllerExportRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/operation/channel/list", requestType = "get" )
    public String getChannelList() {
    return http();
    }


    @Mapping(sub_path = "/operation/crack", requestType = "post" ,contentTpye = "Application/json")
    public String crack (OperationControllerCrackRo ro) {
    return http(ro);
    }


}



