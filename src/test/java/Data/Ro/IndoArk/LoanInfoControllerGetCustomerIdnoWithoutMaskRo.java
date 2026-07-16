package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.LoanInfoController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class LoanInfoControllerGetCustomerIdnoWithoutMaskRo extends LoanInfoController {

    @Param
    public String id;

   static {
      DenpendencesProcessor.roDendenpence(LoanInfoControllerGetCustomerIdnoWithoutMaskRo.class);
   }
}


