package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.AutoOutboundController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AutoOutboundControllerCancelRo extends AutoOutboundController {

    @Param
    public String batchNo;

   static {
      DenpendencesProcessor.roDendenpence(AutoOutboundControllerCancelRo.class);
   }
}


