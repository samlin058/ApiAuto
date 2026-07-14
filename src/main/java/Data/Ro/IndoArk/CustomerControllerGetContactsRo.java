package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.CustomerController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class CustomerControllerGetContactsRo extends CustomerController {

    @Param
    public String id;

   static {
      DenpendencesProcessor.roDendenpence(CustomerControllerGetContactsRo.class);
   }
}


