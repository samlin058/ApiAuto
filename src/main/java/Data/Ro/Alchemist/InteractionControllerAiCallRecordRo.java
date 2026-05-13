package Data.Ro.Alchemist;

import Data.Apis.Alchemist.InteractionController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class InteractionControllerAiCallRecordRo extends InteractionController {

    @Body
    public String conclusion;
    @Body
    public Integer billId;
    @Body
    public String connId;
    @Body
    public String remark;
    @Body
    public String connResult;

   static {
      DenpendencesProcessor.roDendenpence(InteractionControllerAiCallRecordRo.class);
   }
}


