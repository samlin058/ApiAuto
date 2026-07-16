package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.ContactsShowRuleController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class ContactsShowRuleControllerAddRuleConfigRo extends ContactsShowRuleController {

    @Body
    public Integer id;
    @Body
    public List<?> results;

   static {
      DenpendencesProcessor.roDendenpence(ContactsShowRuleControllerAddRuleConfigRo.class);
   }
}


