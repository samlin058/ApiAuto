package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.UserClockInController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class UserClockInControllerPageRo extends UserClockInController {

    @Param
    public String checkInTimeStart;
    @Param
    public String checkInTimeEnd;
    @Param
    public Number clockInDurationStart;
    @Param
    public Number clockInDurationEnd;
    @Param
    public String[] userIds;
    @Param
    public Number current;
    @Param
    public Number pageSize;

   static {
      DenpendencesProcessor.roDendenpence(UserClockInControllerPageRo.class);
   }
}


