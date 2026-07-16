package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.ContactsShowRuleController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class ContactsShowRuleControllerQueryRuleByAssetIdRo extends ContactsShowRuleController {

    @Param
    public String orderNo;
    @Param
    public String disposerCode;

   static {
      DenpendencesProcessor.roDendenpence(ContactsShowRuleControllerQueryRuleByAssetIdRo.class);
   }
}


