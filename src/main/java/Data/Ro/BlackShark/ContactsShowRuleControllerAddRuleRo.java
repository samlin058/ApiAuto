package Data.Ro.BlackShark;

import Data.Apis.BlackShark.ContactsShowRuleController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class ContactsShowRuleControllerAddRuleRo extends ContactsShowRuleController {

    @Body
    public String strategyName;
    @Body
    public String strategyVersion;
    @Body
    public List<?> disposerList;
    @Body
    public String assetPkgNo;
    @Body
    public String strategyTypeCode;
    @Body
    public String strategyCode;
    @Body
    public Boolean status;

   static {
      DenpendencesProcessor.roDendenpence(ContactsShowRuleControllerAddRuleRo.class);
   }
}


