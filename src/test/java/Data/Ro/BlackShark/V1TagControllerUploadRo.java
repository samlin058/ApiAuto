package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.V1TagController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class V1TagControllerUploadRo extends V1TagController {

    @Param
    public String parentTagCode;
    @FileBody
    public String file;
    @Body
    public String assetPkgNo;
    @Body
    public String reqSerialNo;
    @Body
    public String X_Action;

   static {
      DenpendencesProcessor.roDendenpence(V1TagControllerUploadRo.class);
   }
}


