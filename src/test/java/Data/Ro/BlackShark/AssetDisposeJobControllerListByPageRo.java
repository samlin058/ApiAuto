package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.AssetDisposeJobController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssetDisposeJobControllerListByPageRo extends AssetDisposeJobController {

    @Param
    public String type;
    @Param
    public String strategyTypeCode;
    @Param
    public String status;
    @Param
    public String current;
    @Param
    public String pageSize;

   static {
      DenpendencesProcessor.roDendenpence(AssetDisposeJobControllerListByPageRo.class);
   }
}


