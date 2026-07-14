package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.CustomerTagController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class CustomerTagControllerDeleteCustomerTagRo extends CustomerTagController {

    @Param
    public String id;
    @Param
    public String customerTagId;
    @Body
    public String reason;
    @Body
    public String loanId;

   static {
      DenpendencesProcessor.roDendenpence(CustomerTagControllerDeleteCustomerTagRo.class);
   }
}


