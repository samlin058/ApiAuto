package Data.Ro.BlackShark;

import Data.Apis.BlackShark.ImportFileController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class ImportFileControllerUploadRo extends ImportFileController {

    @Body
    public String chunkSign;
    @Body
    public String fileSign;
    @Body
    public String fileName;
    @Body
    public String Code;
    @Body
    public String assetPkgNo;
    @Body
    public String fileSize;
    @Body
    public String chunkNo;
    @FileBody
    public String chunkFile;

   static {
      DenpendencesProcessor.roDendenpence(ImportFileControllerUploadRo.class);
   }
}


