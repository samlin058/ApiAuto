package Data.Ro.BlackShark;

import Data.Apis.BlackShark.BizAddrScoreController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class BizAddrScoreControllerAddrScoreListRo extends BizAddrScoreController {

    @Param
    public String current;
    @Param
    public String pageSize;
    @Param
    public String name;
    @Param
    public String locationCode;

   static {
      DenpendencesProcessor.roDendenpence(BizAddrScoreControllerAddrScoreListRo.class);
   }
}


