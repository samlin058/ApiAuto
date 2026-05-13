package Data.Ro.BlackShark;

import Data.Apis.BlackShark.AutoOutboundController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AutoOutboundControllerDownloadRo extends AutoOutboundController {

    @Param
    public String batchNo;

   static {
      DenpendencesProcessor.roDendenpence(AutoOutboundControllerDownloadRo.class);
   }
}


