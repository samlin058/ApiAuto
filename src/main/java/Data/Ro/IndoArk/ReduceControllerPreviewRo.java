package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.ReduceController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class ReduceControllerPreviewRo extends ReduceController {

    @Param
    public String url;

   static {
      DenpendencesProcessor.roDendenpence(ReduceControllerPreviewRo.class);
   }
}


