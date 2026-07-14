package Data.Ro.Alchemist;

import Data.ApisLayer.Alchemist.OperationController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class OperationControllerImportOperationRo extends OperationController {

    @Param
    public String taskId;
    @Param
    public String validateType;
    @Param
    public String intoPiecesModel;
    @FileBody
    public String file;
    @Body
    public String channel;

   static {
      DenpendencesProcessor.roDendenpence(OperationControllerImportOperationRo.class);
   }
}


