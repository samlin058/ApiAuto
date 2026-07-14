package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.VoiceApiController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class VoiceApiControllerGetDecryptPhoneByEncryptPhoneRo extends VoiceApiController {

    @Param
    public String encryptPhone;

   static {
      DenpendencesProcessor.roDendenpence(VoiceApiControllerGetDecryptPhoneByEncryptPhoneRo.class);
   }
}


