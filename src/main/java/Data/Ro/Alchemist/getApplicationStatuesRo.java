package Data.Ro.Alchemist;

import Data.Apis.Alchemist.LeadController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;

;

@lombok.Data
public class getApplicationStatuesRo extends LeadController {
    @Param
    public String leadId;

    static {
        DenpendencesProcessor.roDendenpence(getApplicationStatuesRo.class);
    }

}
