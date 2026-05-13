package Data.Ro.BlackShark;

import Data.Apis.BlackShark.CommissionController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class CommissionControllerDetailListRo extends CommissionController {

    @Param
    public String year;
    @Param
    public String month;
    @Param
    public String disposeCode;

   static {
      DenpendencesProcessor.roDendenpence(CommissionControllerDetailListRo.class);
   }
}


