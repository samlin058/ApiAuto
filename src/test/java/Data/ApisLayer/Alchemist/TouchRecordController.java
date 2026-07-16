package Data.ApisLayer.Alchemist;
import Data.Ro.Alchemist.TouchRecordControllerPageRo;

import Data.Ro.Alchemist.TouchRecordControllerExportRo;

import Data.Ro.Alchemist.TouchRecordControllerTouchRo;



import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class TouchRecordController extends ApiBase {

    @Mapping(sub_path = "/touchRecord/page", requestType = "post" ,contentTpye = "Application/json")
    public String page (TouchRecordControllerPageRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/touchRecord/export", requestType = "get" )
    public String export (TouchRecordControllerExportRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/touchRecord/touch", requestType = "post" ,contentTpye = "Application/json")
    public String touch (TouchRecordControllerTouchRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/touchRecord/bizType", requestType = "get" )
    public String bizTypeList() {
    return http();
    }



}



