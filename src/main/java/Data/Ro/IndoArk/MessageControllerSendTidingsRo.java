package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.MessageController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class MessageControllerSendTidingsRo extends MessageController {

    @Body
    public String sender;
    @Body
    public List<?> receivers;
    @Body
    public String level;
    @Body
    public String createdTime;
    @Body
    public String title;
    @Body
    public String type;
    @Body
    public String content;

   static {
      DenpendencesProcessor.roDendenpence(MessageControllerSendTidingsRo.class);
   }
}


