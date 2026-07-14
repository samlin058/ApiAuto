package Data.Ro.Alchemist;

import Data.ApisLayer.Alchemist.InteractionController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class InteractionControllerAiChatContentRo extends InteractionController {

    @Param
    public String connId;

   static {
      DenpendencesProcessor.roDendenpence(InteractionControllerAiChatContentRo.class);
   }
}


