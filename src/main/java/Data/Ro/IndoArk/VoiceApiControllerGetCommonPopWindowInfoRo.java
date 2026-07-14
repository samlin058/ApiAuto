package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.VoiceApiController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class VoiceApiControllerGetCommonPopWindowInfoRo extends VoiceApiController {

    @Param
    public Integer loanId;
    @Param
    public String maskId;

   static {
      DenpendencesProcessor.roDendenpence(VoiceApiControllerGetCommonPopWindowInfoRo.class);
   }
}


