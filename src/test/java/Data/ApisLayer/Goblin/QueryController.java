package Data.ApisLayer.Goblin;
import Data.Ro.Goblin.QueryControllerExportCollectionCasesRo;



import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class QueryController extends ApiBase {

    @Mapping(sub_path = "/query/collectionCases/export", requestType = "get" )
    public String exportCollectionCases (QueryControllerExportCollectionCasesRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/query/file/collectionCases", requestType = "post" ,contentTpye = "Application/json")
    public String getFileQueryCollectionCase() {
    return http();
    }



}



