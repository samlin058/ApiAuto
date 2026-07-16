package Data.Ro.Alchemist;

import Data.ApisLayer.Alchemist.ConfigController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class ConfigControllerLeadConfigRo extends ConfigController {

    @Body
    public Integer maxMobileCheckTimesPerPerson;

   static {
      DenpendencesProcessor.roDendenpence(ConfigControllerLeadConfigRo.class);
   }
}


