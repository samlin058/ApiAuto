package Data.Ro.Alchemist;

import Data.Apis.Alchemist.LeadController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class LeadControllerCreateShortLinkRo extends LeadController {

    @Body
    public Integer activityId;
    @Body
    public Integer billId;

   static {
      DenpendencesProcessor.roDendenpence(LeadControllerCreateShortLinkRo.class);
   }
}


