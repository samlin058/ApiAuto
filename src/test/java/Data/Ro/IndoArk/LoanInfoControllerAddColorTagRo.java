package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.LoanInfoController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class LoanInfoControllerAddColorTagRo extends LoanInfoController {

    @Body
    public String color;
    @Body
    public Integer id;

   static {
      DenpendencesProcessor.roDendenpence(LoanInfoControllerAddColorTagRo.class);
   }
}


