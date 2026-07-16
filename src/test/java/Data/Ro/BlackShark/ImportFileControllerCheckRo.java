package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.ImportFileController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class ImportFileControllerCheckRo extends ImportFileController {

    @Body
    public String fileSign;
    @Body
    public String fileName;
    @Body
    public String assetPkgNo;
    @Body
    public Integer chunkSize;
    @Body
    public Integer fileSize;
    @Body
    public Integer chunkNum;

   static {
      DenpendencesProcessor.roDendenpence(ImportFileControllerCheckRo.class);
   }
}


