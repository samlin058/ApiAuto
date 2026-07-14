package Data.ApisLayer.Alchemist;
import Data.Ro.Alchemist.BillManageControllerQueryBillsManageRo;

import Data.Ro.Alchemist.BillManageControllerCountRo;

import Data.Ro.Alchemist.BillManageControllerSendBatchSmsRo;

import Data.Ro.Alchemist.BillManageControllerAssignBatchRo;

import Data.Ro.Alchemist.BillManageControllerSelectAssignRo;







import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class BillManageController extends ApiBase {

    @Mapping(sub_path = "/bill/manage/query", requestType = "get" )
    public String queryBillsManage (BillManageControllerQueryBillsManageRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/bill/manage/count", requestType = "get" )
    public String count (BillManageControllerCountRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/bill/manage/send/batch/sms", requestType = "post" ,contentTpye = "Application/json")
    public String sendBatchSms (BillManageControllerSendBatchSmsRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/bill/manage/assign/batch", requestType = "post" ,contentTpye = "Application/json")
    public String assignBatch (BillManageControllerAssignBatchRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/bill/manage/select/assign", requestType = "post" ,contentTpye = "Application/json")
    public String selectAssign (BillManageControllerSelectAssignRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/bill/manage/assign/queues", requestType = "get" )
    public String assignQueues() {
    return http();
    }


    @Mapping(sub_path = "/bill/manage/unOperating/status", requestType = "get" )
    public String unOperatingStatus() {
    return http();
    }


    @Mapping(sub_path = "/bill/manage/callStatus/enums", requestType = "get" )
    public String callStatusEnums() {
    return http();
    }



}



