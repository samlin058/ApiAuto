package Data.Ro.Alchemist;

import Data.Apis.Alchemist.ActivityConfigController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class ActivityConfigControllerSaveOrUpdateRo extends ActivityConfigController {

    @Body
    public String activityUrl;
    @Body
    public String activityName;
    @Body
    public Integer id;
    @Body
    public Boolean status;

   static {
      DenpendencesProcessor.roDendenpence(ActivityConfigControllerSaveOrUpdateRo.class);
   }
}


