package Data.Ro.BlackShark;

import Data.Apis.BlackShark.ContactsShowRuleController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class ContactsShowRuleControllerRuleListRo extends ContactsShowRuleController {

    @Param
    public String assetPkgNo;
    @Param
    public String strategyName;
    @Param
    public String status;

   static {
      DenpendencesProcessor.roDendenpence(ContactsShowRuleControllerRuleListRo.class);
   }
}


