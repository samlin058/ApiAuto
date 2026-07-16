package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.VoiceApiController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class VoiceApiControllerAutomaticCruiseCallRo extends VoiceApiController {

    @Body
    public String mobile;
    @Body
    public Integer loanId;

   static {
      DenpendencesProcessor.roDendenpence(VoiceApiControllerAutomaticCruiseCallRo.class);
   }
}


