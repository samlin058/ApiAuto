package Data.Ro.Alchemist;

import Data.Apis.Alchemist.TaskController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class TaskControllerPreviewAwmSqlRo extends TaskController {

    @Body
    public Integer intoPiecesModel;
    @Body
    public String sqlConfig;
    @Body
    public String params;

   static {
      DenpendencesProcessor.roDendenpence(TaskControllerPreviewAwmSqlRo.class);
   }
}


