package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.ReduceController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class ReduceControllerDeleteRo extends ReduceController {


    @Body
    public String url;

   static {
      DenpendencesProcessor.roDendenpence(ReduceControllerDeleteRo.class);
   }
}


