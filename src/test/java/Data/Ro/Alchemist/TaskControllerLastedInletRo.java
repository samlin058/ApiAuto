package Data.Ro.Alchemist;

import Data.ApisLayer.Alchemist.TaskController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class TaskControllerLastedInletRo extends TaskController {

    @Param
    public String id;

   static {
      DenpendencesProcessor.roDendenpence(TaskControllerLastedInletRo.class);
   }
}


