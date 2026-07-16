package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.PrepareOperationController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class PrepareOperationControllerRecordPagesRo extends PrepareOperationController {

    @Param
    public String loanId;
    @Param
    public Integer current;
    @Param
    public Integer pageSize;

   static {
      DenpendencesProcessor.roDendenpence(PrepareOperationControllerRecordPagesRo.class);
   }
}


