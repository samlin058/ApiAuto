package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.BizAddrScoreController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class BizAddrScoreControllerGetByParentLocationCodeRo extends BizAddrScoreController {

    @Param
    public String locationCode;

   static {
      DenpendencesProcessor.roDendenpence(BizAddrScoreControllerGetByParentLocationCodeRo.class);
   }
}


