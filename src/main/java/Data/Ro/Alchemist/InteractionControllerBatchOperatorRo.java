package Data.Ro.Alchemist;

import Data.Apis.Alchemist.InteractionController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class InteractionControllerBatchOperatorRo extends InteractionController {

    @Body
    public String conclusion;
    @Body
    public List<?> billIds;
    @Body
    public Integer smsTemplateId;
    @Body
    public String remark;

   static {
      DenpendencesProcessor.roDendenpence(InteractionControllerBatchOperatorRo.class);
   }
}


