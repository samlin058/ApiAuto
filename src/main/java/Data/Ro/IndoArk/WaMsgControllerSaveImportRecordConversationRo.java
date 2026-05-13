package Data.Ro.IndoArk;

import Data.Apis.IndoArk.WaMsgController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;

import java.util.List;


@lombok.Data
public class WaMsgControllerSaveImportRecordConversationRo extends WaMsgController {

    @Body
    public String conversationId;
    @Body
    public Integer unreadCount;
    @Body
    public List<?> messages;

   static {
      DenpendencesProcessor.roDendenpence(WaMsgControllerSaveImportRecordConversationRo.class);
   }
}


