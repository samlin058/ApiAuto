package Data.Ro.Alchemist;

import Data.ApisLayer.Alchemist.FileController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class FileControllerImportLeadRo extends FileController {

    @Param
    public String taskId;
    @Param
    public String validateType;
    @Param
    public String intoPiecesModel;
    @FileBody
    public String file;


   static {
      DenpendencesProcessor.roDendenpence(FileControllerImportLeadRo.class);
   }
}


