package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.CustomerController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class CustomerControllerCancelFavoriteRo extends CustomerController {

    @Body
    public Integer contactId;
    @Body
    public Integer loanId;

   static {
      DenpendencesProcessor.roDendenpence(CustomerControllerCancelFavoriteRo.class);
   }
}


