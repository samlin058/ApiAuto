package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.AutoCallController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class AutoCallControllerSaveAutoCallTaskRo extends AutoCallController {

    @Body
    public Boolean retryCall;
    @Body
    public Integer callTemplate;
    @Body
    public Integer retryCount;
    @Body
    public String name;
    @Body
    public String startTime;
    @Body
    public Integer retryInterval;
    @Body
    public String endTime;
    @Body
    public List<?> loanIds;
    @Body
    public String loanSourceType;
    @Body
    public String callType;

   static {
      DenpendencesProcessor.roDendenpence(AutoCallControllerSaveAutoCallTaskRo.class);
   }
}


