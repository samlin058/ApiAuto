package Data.ApisLayer.Alchemist;


import Data.Ro.Alchemist.TaskControllerAssignListRo;
import Data.Ro.Alchemist.TaskControllerAwmInletRo;
import Data.Ro.Alchemist.TaskControllerGetTaskResultsRo;
import Data.Ro.Alchemist.TaskControllerPreviewAwmSqlRo;
import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class TaskController extends ApiBase {

    @Mapping(sub_path = "/tasks/{id}/assign", requestType = "post", contentTpye = "Application/json")
    public String assign() {
        return http();
    }


    @Mapping(sub_path = "/tasks/{id}/assignList", requestType = "get")
    public String assignList(TaskControllerAssignListRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/tasks/results", requestType = "get")
    public String getTaskResults(TaskControllerGetTaskResultsRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/tasks/{batchNo}/downloadFailRecord", requestType = "get")
    public String downloadFailRecord() {
        return http();
    }


    @Mapping(sub_path = "/tasks/awmParams", requestType = "get")
    public String getAwmParams() {
        return http();
    }


    @Mapping(sub_path = "/tasks/{taskId}/awmSql", requestType = "get")
    public String getAwmSql() {
        return http();
    }


    @Mapping(sub_path = "/tasks/previewAwmSql", requestType = "post", contentTpye = "Application/json")
    public String previewAwmSql(TaskControllerPreviewAwmSqlRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/tasks/{taskId}/awmInlet", requestType = "post", contentTpye = "Application/json")
    public String awmInlet(TaskControllerAwmInletRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/tasks/{taskId}/results", requestType = "get")
    public String getResultsByTaskId() {
        return http();
    }


    @Mapping(sub_path = "/tasks/{id}/lastedInlet", requestType = "get")
    public String lastedInlet() {
        return http();
    }


}



