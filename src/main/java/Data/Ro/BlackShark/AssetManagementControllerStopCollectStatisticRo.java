package Data.Ro.BlackShark;

import Data.Apis.BlackShark.AssetManagementController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssetManagementControllerStopCollectStatisticRo extends AssetManagementController {

    @Param
    public String preAssetPkgId;

   static {
      DenpendencesProcessor.roDendenpence(AssetManagementControllerStopCollectStatisticRo.class);
   }
}


