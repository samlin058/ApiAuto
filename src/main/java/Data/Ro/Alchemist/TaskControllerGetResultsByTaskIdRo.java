package Data.Ro.Alchemist;

import Data.Apis.Alchemist.TaskController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class TaskControllerGetResultsByTaskIdRo extends TaskController {

    @Param
    public String taskId;

   static {
      DenpendencesProcessor.roDendenpence(TaskControllerGetResultsByTaskIdRo.class);
   }
}


