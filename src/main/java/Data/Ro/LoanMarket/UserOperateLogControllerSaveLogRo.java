package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.UserOperateLogController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class UserOperateLogControllerSaveLogRo extends UserOperateLogController {

   static {
      DenpendencesProcessor.roDendenpence(UserOperateLogControllerSaveLogRo.class);
   }

    @Body
    public String channel;
    @Body
    public String mobile;
    @Body
    public String page;
    @Body
    public String type;
    @Body
    public String info;


}


