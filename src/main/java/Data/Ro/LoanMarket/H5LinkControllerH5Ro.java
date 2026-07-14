package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.H5LinkController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class H5LinkControllerH5Ro extends H5LinkController {

   static {
      DenpendencesProcessor.roDendenpence(H5LinkControllerH5Ro.class);
   }

    @Param
    public String channel;
    @Param
    public String partnerCode;
    @Param
    public String type;


}


