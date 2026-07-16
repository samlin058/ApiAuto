package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.AssetDisposerController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssetDisposerControllerRepaymentRateRankingRo extends AssetDisposerController {

    @Param
    public String pageSize;
    @Param
    public String current;
    @Param
    public String bizScope;

   static {
      DenpendencesProcessor.roDendenpence(AssetDisposerControllerRepaymentRateRankingRo.class);
   }
}


