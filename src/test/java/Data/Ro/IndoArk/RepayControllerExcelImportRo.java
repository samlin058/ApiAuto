package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.RepayController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class RepayControllerExcelImportRo extends RepayController {

    @FileBody
    public String file;
    @Body
    public Integer loanSourceId;

   static {
      DenpendencesProcessor.roDendenpence(RepayControllerExcelImportRo.class);
   }
}


