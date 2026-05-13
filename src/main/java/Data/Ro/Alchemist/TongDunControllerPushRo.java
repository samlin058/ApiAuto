package Data.Ro.Alchemist;

import Data.Apis.Alchemist.TongDunController;
import Service.Annotations.AnnotationLibrary.JsonArray;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;

@JsonArray
@lombok.Data
public class TongDunControllerPushRo extends TongDunController {

    @Body
    public Object extparam;
    @Body
    public String mobileMd5;

   static {
      DenpendencesProcessor.roDendenpence(TongDunControllerPushRo.class);
   }
}


