package Data.Ro.Alchemist;

import Data.Apis.Alchemist.TaskController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class TaskControllerAssignListRo extends TaskController {

    @Param
    public Integer id;
    @Param
    public String current;
    @Param
    public String pageSize;

   static {
      DenpendencesProcessor.roDendenpence(TaskControllerAssignListRo.class);
   }
}


