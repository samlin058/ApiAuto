package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.UserController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class UserControllerAbandonReasonsRo extends UserController {

   static {
      DenpendencesProcessor.roDendenpence(UserControllerAbandonReasonsRo.class);
   }

    @Param
    public Integer uid;
    @Param
    public String page;


}


