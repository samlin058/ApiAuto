package Data.Ro.Alchemist;

import Data.Apis.Alchemist.InteractionController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class InteractionControllerPushAiRo extends InteractionController {

    @Param
    public String billId;

   static {
      DenpendencesProcessor.roDendenpence(InteractionControllerPushAiRo.class);
   }
}


