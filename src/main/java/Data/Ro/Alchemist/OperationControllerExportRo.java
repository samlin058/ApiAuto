package Data.Ro.Alchemist;

import Data.Apis.Alchemist.OperationController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class OperationControllerExportRo extends OperationController {

    @Param
    public String id;

   static {
      DenpendencesProcessor.roDendenpence(OperationControllerExportRo.class);
   }
}


