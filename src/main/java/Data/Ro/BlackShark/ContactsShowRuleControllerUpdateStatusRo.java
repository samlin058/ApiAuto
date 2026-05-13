package Data.Ro.BlackShark;

import Data.Apis.BlackShark.ContactsShowRuleController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class ContactsShowRuleControllerUpdateStatusRo extends ContactsShowRuleController {

    @Param
    public Integer ruleId;
    @Param
    public String status;

   static {
      DenpendencesProcessor.roDendenpence(ContactsShowRuleControllerUpdateStatusRo.class);
   }
}


