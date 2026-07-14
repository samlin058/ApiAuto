package Data.Ro.Alchemist;

import Data.ApisLayer.Alchemist.InteractionController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class InteractionControllerOperateRo extends InteractionController {

    @Body
    public String conclusion;
    @Body
    public List<?> billIds;
    @Body
    public String couponType;
    @Body
    public Integer smsTemplateId;
    @Body
    public String remark;
    @Body
    public List<?> businessNames;

   static {
      DenpendencesProcessor.roDendenpence(InteractionControllerOperateRo.class);
   }
}


