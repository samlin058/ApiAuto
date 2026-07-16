package Data.Ro.Alchemist;

import Data.ApisLayer.Alchemist.FileController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class FileControllerLeadFileQueryRo extends FileController {

    @Param
    public String businessId;

   static {
      DenpendencesProcessor.roDendenpence(FileControllerLeadFileQueryRo.class);
   }
}


