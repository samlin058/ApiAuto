package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.ImportFileController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class ImportFileControllerMergeRo extends ImportFileController {

    @Body
    public String fileSign;
    @Body
    public String fileName;
    @Body
    public String Code;
    @Body
    public String assetPkgNo;
    @Body
    public Integer chunkNum;
    @Body
    public String id;

   static {
      DenpendencesProcessor.roDendenpence(ImportFileControllerMergeRo.class);
   }
}


