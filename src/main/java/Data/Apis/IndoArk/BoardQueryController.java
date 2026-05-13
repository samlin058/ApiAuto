package Data.Apis.IndoArk;
import Data.Ro.IndoArk.BoardQueryControllerQueryBoardAssignmentsRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class BoardQueryController extends ApiBase {

    @Mapping(sub_path = "/query/board/assignments", requestType = "get" )
    public String queryBoardAssignments (BoardQueryControllerQueryBoardAssignmentsRo ro) {
    return http(ro);
    }


}



