package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.RepayController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class RepayControllerApproveRo extends RepayController {

    @Body
    public List<?> repayIdList;
    @Body
    public String status;

   static {
      DenpendencesProcessor.roDendenpence(RepayControllerApproveRo.class);
   }
}


