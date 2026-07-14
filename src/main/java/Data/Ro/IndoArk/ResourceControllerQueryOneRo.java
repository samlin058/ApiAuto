package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.ResourceController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class ResourceControllerQueryOneRo extends ResourceController {

    @Param
    public String id;

   static {
      DenpendencesProcessor.roDendenpence(ResourceControllerQueryOneRo.class);
   }
}


