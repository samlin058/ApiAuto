package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.AutoCallController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AutoCallControllerAutoCallTaskPageRo extends AutoCallController {

    @Param
    public String name;
    @Param
    public Number callTemplate;
    @Param
    public String status;
    @Param
    public String startTime;
    @Param
    public String endTime;
    @Param
    public Number current;
    @Param
    public Number pageSize;

   static {
      DenpendencesProcessor.roDendenpence(AutoCallControllerAutoCallTaskPageRo.class);
   }
}


