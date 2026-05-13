package Data.Ro.BlackShark;

import Data.Apis.BlackShark.ContactController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class ContactControllerInBoundRo extends ContactController {

    @Body
    public Integer contactId;
    @Body
    public String assetId;
    @Body
    public String linkupMask;

   static {
      DenpendencesProcessor.roDendenpence(ContactControllerInBoundRo.class);
   }
}


