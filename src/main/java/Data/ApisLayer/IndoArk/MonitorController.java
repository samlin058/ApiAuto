package Data.ApisLayer.IndoArk;
import Data.Ro.IndoArk.MonitorControllerAddServiceTimeRo;

import Data.Ro.IndoArk.MonitorControllerAddPingDataRo;

import Data.Ro.IndoArk.MonitorControllerGetServiceTimeRo;

import Data.Ro.IndoArk.MonitorControllerGetPingTimeRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class MonitorController extends ApiBase {

    @Mapping(sub_path = "/monitor/service/time", requestType = "post" ,contentTpye = "application/json")
    public String addServiceTime (MonitorControllerAddServiceTimeRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/monitor/service/ping", requestType = "post" ,contentTpye = "application/json")
    public String addPingData (MonitorControllerAddPingDataRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/monitor/service/time/rate", requestType = "get" )
    public String getServiceTime (MonitorControllerGetServiceTimeRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/monitor/ping/rate", requestType = "get" )
    public String getPingTime (MonitorControllerGetPingTimeRo ro) {
    return http(ro);
    }


}



