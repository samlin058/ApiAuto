package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.VoiceApiController;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class VoiceApiControllerGetInboundLoanIdRo extends VoiceApiController {

    @Param
    public String mobile;

   static {
      DenpendencesProcessor.roDendenpence(VoiceApiControllerGetInboundLoanIdRo.class);
   }
}


