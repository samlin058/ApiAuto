package Data.Ro.BlackShark;

import Data.Apis.BlackShark.AssetDisposeOpenController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class AssetDisposeOpenControllerDisposeResultRo extends AssetDisposeOpenController {

    @Body
    public String sendId;
    @Body
    public String bizType;
    @Body
    public String message;
    @Body
    public String status;

   static {
      DenpendencesProcessor.roDendenpence(AssetDisposeOpenControllerDisposeResultRo.class);
   }
}


