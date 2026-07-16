package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.AutoCallController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AutoCallControllerCallAutoPreQueryRo extends AutoCallController {

    @Param
    public Number taskId;
    @Param
    public String callResult;
    @Param
    public Number startOverdueDays;
    @Param
    public Number endOverdueDays;

   static {
      DenpendencesProcessor.roDendenpence(AutoCallControllerCallAutoPreQueryRo.class);
   }
}


