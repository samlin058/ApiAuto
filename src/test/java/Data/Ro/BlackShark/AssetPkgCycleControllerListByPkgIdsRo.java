package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.AssetPkgCycleController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssetPkgCycleControllerListByPkgIdsRo extends AssetPkgCycleController {

    @Param
    public String assetPkgIds;

   static {
      DenpendencesProcessor.roDendenpence(AssetPkgCycleControllerListByPkgIdsRo.class);
   }

    public AssetPkgCycleControllerListByPkgIdsRo(String assetPkgIds) {
        this.assetPkgIds = assetPkgIds;
    }
}


