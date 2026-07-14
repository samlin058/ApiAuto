package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.ReservedController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class ReservedControllerExecuteRo extends ReservedController {

    @Body
    public String approveStatus;
    @Body
    public List<?> taskInstances;
    @Body
    public String approveFeedback;

   static {
      DenpendencesProcessor.roDendenpence(ReservedControllerExecuteRo.class);
   }
}


