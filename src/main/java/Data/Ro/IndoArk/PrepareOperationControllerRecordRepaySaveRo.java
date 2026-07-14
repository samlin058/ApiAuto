package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.PrepareOperationController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class PrepareOperationControllerRecordRepaySaveRo extends PrepareOperationController {

    @Body
    public Number amount;
    @Body
    public String type;
    @Body
    public Integer loanId;

   static {
      DenpendencesProcessor.roDendenpence(PrepareOperationControllerRecordRepaySaveRo.class);
   }
}


