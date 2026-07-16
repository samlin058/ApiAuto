package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.BoardQueryController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class BoardQueryControllerQueryBoardAssignmentsRo extends BoardQueryController {

    @Param
    public String boardType;
    @Param
    public Integer current;
    @Param
    public Integer pageSize;

   static {
      DenpendencesProcessor.roDendenpence(BoardQueryControllerQueryBoardAssignmentsRo.class);
   }
}


