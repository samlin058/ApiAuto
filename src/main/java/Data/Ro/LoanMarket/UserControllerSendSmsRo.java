package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.UserController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class UserControllerSendSmsRo extends UserController {

   static {
      DenpendencesProcessor.roDendenpence(UserControllerSendSmsRo.class);
   }

    @Body
    public String mobile;
    @Body
    public String channel;


    public UserControllerSendSmsRo(String mobile, String channel) {
        this.mobile =mobile;
        this.channel =channel;
    }
}


