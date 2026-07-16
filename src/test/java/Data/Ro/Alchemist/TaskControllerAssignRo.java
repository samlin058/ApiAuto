package Data.Ro.Alchemist;

import Data.ApisLayer.Alchemist.TaskController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class TaskControllerAssignRo extends TaskController {

    @Param
    public String id;

   static {
      DenpendencesProcessor.roDendenpence(TaskControllerAssignRo.class);
   }
}


