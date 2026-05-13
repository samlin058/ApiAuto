package Data.Ro.Alchemist;

import Data.Apis.Alchemist.FileController;
import Service.Annotations.AnnotationLibrary.FileBody;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;

@lombok.Data
public class ImportLeadRo extends FileController {
    @Param
    public String taskId;
    @Param
    public String validateType;
    @Param
    public String intoPiecesModel;
    @FileBody
    public String file;

    static {
        DenpendencesProcessor.roDendenpence(ImportLeadRo.class);
    }

}
