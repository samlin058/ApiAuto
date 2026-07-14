package Data.Ro.Alchemist;

import Data.ApisLayer.Alchemist.LeadController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class LeadControllerGetApplicationStatuesRo extends LeadController {

    @Param
    public String leadId;

   static {
      DenpendencesProcessor.roDendenpence(LeadControllerGetApplicationStatuesRo.class);
   }
}


