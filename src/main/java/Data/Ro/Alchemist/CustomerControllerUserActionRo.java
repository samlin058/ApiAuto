package Data.Ro.Alchemist;

import Data.Apis.Alchemist.CustomerController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class CustomerControllerUserActionRo extends CustomerController {

    @Body
    public String dataTime;
    @Body
    public String ip;
    @Body
    public String mobile;
    @Body
    public String channel;
    @Body
    public String action;
    @Body
    public String status;

   static {
      DenpendencesProcessor.roDendenpence(CustomerControllerUserActionRo.class);
   }
}


