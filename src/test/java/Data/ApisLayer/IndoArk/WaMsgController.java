package Data.ApisLayer.IndoArk;
import Data.Ro.IndoArk.WaMsgControllerLoanImportRo;







import Data.Ro.IndoArk.WaMsgControllerSaveImportRecordConversationRo;
import java.util.List;






import Data.Ro.IndoArk.WaMsgControllerSyncWaConversationTimeoutRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class WaMsgController extends ApiBase {

    @Mapping(sub_path = "/loan/dispose/wa/import", requestType = "post" ,contentTpye = "multipart/form-data")
    public String loanImport (WaMsgControllerLoanImportRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/loan/dispose/wa/{loanId}/messagesClear", requestType = "post" )
    public String waMessagesClear() {
    return http();
    }


    @Mapping(sub_path = "/loan/dispose/wa/{loanId}/messages", requestType = "get" )
    public String getWaMessages() {
    return http();
    }


    @Mapping(sub_path = "/loan/dispose/wa/{loanId}/zipFile", requestType = "get" )
    public String getWaZipFile() {
    return http();
    }


    @Mapping(sub_path = "/loan/dispose/wa/import/record/conversation", requestType = "post" ,contentTpye = "application/json")
    public String saveImportRecordConversation(List<WaMsgControllerSaveImportRecordConversationRo> ro) {
    return http(ro);
    }


    @Mapping(sub_path = "/loan/dispose/wa/import/record/{id}/conversation", requestType = "get" )
    public String getImportRecordConversations() {
    return http();
    }


    @Mapping(sub_path = "/loan/dispose/wa/import/record/conversation/{id}", requestType = "get" )
    public String getImportRecordConversation() {
    return http();
    }


    @Mapping(sub_path = "/loan/dispose/wa/import/record/{id}/sync", requestType = "post" )
    public String syncImportRecord() {
    return http();
    }


    @Mapping(sub_path = "/loan/dispose/wa/import/record/timeout", requestType = "post" ,contentTpye = "application/json")
    public String syncWaConversationTimeout (WaMsgControllerSyncWaConversationTimeoutRo ro) {
    return http(ro);
    }


}



