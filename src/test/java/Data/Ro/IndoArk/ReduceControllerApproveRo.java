package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.ReduceController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class ReduceControllerApproveRo extends ReduceController {

    @Body
    public Integer reduceId;
    @Body
    public String status;

   static {
      DenpendencesProcessor.roDendenpence(ReduceControllerApproveRo.class);
   }
}


