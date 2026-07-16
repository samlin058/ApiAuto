package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.AutoCallController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;


@lombok.Data
public class AutoCallControllerUploadAutoCallLoanRo extends AutoCallController {

    @FileBody
    public String file;

   static {
      DenpendencesProcessor.roDendenpence(AutoCallControllerUploadAutoCallLoanRo.class);
   }
}


