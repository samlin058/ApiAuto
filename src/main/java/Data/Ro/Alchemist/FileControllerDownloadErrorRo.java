package Data.Ro.Alchemist;

import Data.Apis.Alchemist.FileController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class FileControllerDownloadErrorRo extends FileController {

    @Param
    public String taskId;
    @Param
    public String batchNo;

   static {
      DenpendencesProcessor.roDendenpence(FileControllerDownloadErrorRo.class);
   }
}


