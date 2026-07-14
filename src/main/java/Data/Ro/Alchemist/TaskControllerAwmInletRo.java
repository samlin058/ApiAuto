package Data.Ro.Alchemist;

import Data.ApisLayer.Alchemist.TaskController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class TaskControllerAwmInletRo extends TaskController {

    @Param
    public String taskId;
    @Body
    public Integer intoPiecesModel;
    @Body
    public String sqlConfig;
    @Body
    public String params;

   static {
      DenpendencesProcessor.roDendenpence(TaskControllerAwmInletRo.class);
   }
}


