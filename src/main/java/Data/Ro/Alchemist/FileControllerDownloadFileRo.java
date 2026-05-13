package Data.Ro.Alchemist;

import Data.Apis.Alchemist.FileController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class FileControllerDownloadFileRo extends FileController {

    @Param
    public String templateCode;

   static {
      DenpendencesProcessor.roDendenpence(FileControllerDownloadFileRo.class);
   }
}


