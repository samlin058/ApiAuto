package Data.Ro.Alchemist;

import Data.ApisLayer.Alchemist.BillManageController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class BillManageControllerSelectAssignRo extends BillManageController {

    @Body
    public Integer queueId;
    @Body
    public List<?> billIds;

   static {
      DenpendencesProcessor.roDendenpence(BillManageControllerSelectAssignRo.class);
   }
}


