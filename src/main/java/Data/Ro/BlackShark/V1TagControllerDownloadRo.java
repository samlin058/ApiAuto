package Data.Ro.BlackShark;

import Data.Apis.BlackShark.V1TagController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class V1TagControllerDownloadRo extends V1TagController {

    @Param
    public String parentTagCode;
    @Param
    public String reqSerialNo;
    @Param
    public String fileType;

   static {
      DenpendencesProcessor.roDendenpence(V1TagControllerDownloadRo.class);
   }
}


