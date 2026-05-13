package Data.Apis.Alchemist;

import Data.Ro.Alchemist.FileControllerDownloadErrorRo;
import Data.Ro.Alchemist.FileControllerDownloadFileRo;
import Data.Ro.Alchemist.FileControllerImportLeadRo;
import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;

public class FileController extends ApiBase {
    @Mapping(sub_path = "/files/outLead/import", requestType = "post", contentTpye = "multipart/form-data")
    public Object importLead(FileControllerImportLeadRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/files/outLead/template", requestType = "get")
    public String downloadFile(FileControllerDownloadFileRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/files/outLead/{businessId}", requestType = "get")
    public String leadFileQuery() {
        return http();
    }

    @Mapping(sub_path = "/files/outLead/downloadError/{taskId}", requestType = "get")
    public String downloadError(FileControllerDownloadErrorRo ro) {
        return http(ro);
    }

}
