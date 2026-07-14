package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.ContactsShowRuleController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class ContactsShowRuleControllerCheckRuleRo extends ContactsShowRuleController {

    @Body
    public String strategyName;
    @Body
    public String assetPkgNo;
    @Body
    public String strategyTypeCode;
    @Body
    public String strategyCode;
    @Body
    public Boolean status;

   static {
      DenpendencesProcessor.roDendenpence(ContactsShowRuleControllerCheckRuleRo.class);
   }
}


