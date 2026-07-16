package Data.ApisLayer.IndoArk;
import Data.Ro.IndoArk.AutoCallControllerPreQueryRo;



import Data.Ro.IndoArk.AutoCallControllerUploadAutoCallLoanRo;
import Data.Ro.IndoArk.AutoCallControllerSaveAutoCallTaskRo;
import Data.Ro.IndoArk.AutoCallControllerAutoCallTaskPageRo;
import Data.Ro.IndoArk.AutoCallControllerCallAutoPreQueryRo;
import Data.Ro.IndoArk.AutoCallControllerLinkupCallRecordCallbackRo;





import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class AutoCallController extends ApiBase {

    @Mapping(sub_path = "/call/auto/loan/pre/query", requestType = "get" )
    public String preQuery (AutoCallControllerPreQueryRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/call/auto/loan/template/export", requestType = "get" )
    public String downloadAutoCallLoanTemplate() {
    return http();
    }


    @Mapping(sub_path = "/call/auto/loan/upload", requestType = "post" ,contentTpye = "multipart/form-data")
    public String uploadAutoCallLoan (AutoCallControllerUploadAutoCallLoanRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/call/auto/task", requestType = "post" ,contentTpye = "application/json")
    public String saveAutoCallTask (AutoCallControllerSaveAutoCallTaskRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/call/auto/task/page", requestType = "get" )
    public String autoCallTaskPage (AutoCallControllerAutoCallTaskPageRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/call/auto/task/{id}/cancel", requestType = "post" )
    public String cancelAutoCallTask() {
    return http();
    }


    @Mapping(sub_path = "/call/auto/type", requestType = "get" )
    public String getCallAutoTypes() {
    return http();
    }


    @Mapping(sub_path = "/call/auto/template", requestType = "get" )
    public String getCallAutoTemplates() {
    return http();
    }


    @Mapping(sub_path = "/call/auto/status", requestType = "get" )
    public String getCallAutoStatus() {
    return http();
    }


    @Mapping(sub_path = "/call/auto/callAuto/pre/query", requestType = "get" )
    public String callAutoPreQuery (AutoCallControllerCallAutoPreQueryRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/call/auto/task/{id}/item/export", requestType = "get" )
    public String exportTaskItems() {
    return http();
    }


    @Mapping(sub_path = "/call/auto/callRecord/callback", requestType = "post" ,contentTpye = "application/json")
    public String linkupCallRecordCallback (AutoCallControllerLinkupCallRecordCallbackRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/call/auto/task/{id}", requestType = "get" )
    public String saveAutoCallTask() {
    return http();
    }


    @Mapping(sub_path = "/call/auto/template/{value}/info", requestType = "get" )
    public String getCallAutoTemplateInfo() {
    return http();
    }



}



