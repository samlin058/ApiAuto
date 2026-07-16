package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.PrepareOperationController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class PrepareOperationControllerRecordDeleteRo extends PrepareOperationController {

    @Param
    public String recordId;

   static {
      DenpendencesProcessor.roDendenpence(PrepareOperationControllerRecordDeleteRo.class);
   }
}


