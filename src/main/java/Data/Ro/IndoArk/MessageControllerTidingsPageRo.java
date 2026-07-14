package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.MessageController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class MessageControllerTidingsPageRo extends MessageController {

    @Param
    public String status;
    @Param
    public Integer current;
    @Param
    public Integer pageSize;

   static {
      DenpendencesProcessor.roDendenpence(MessageControllerTidingsPageRo.class);
   }
}


