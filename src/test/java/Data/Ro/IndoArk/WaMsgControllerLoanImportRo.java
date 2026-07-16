package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.WaMsgController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class WaMsgControllerLoanImportRo extends WaMsgController {

    @FileBody
    public String file;
    @Body
    public Integer loanId;

   static {
      DenpendencesProcessor.roDendenpence(WaMsgControllerLoanImportRo.class);
   }
}


