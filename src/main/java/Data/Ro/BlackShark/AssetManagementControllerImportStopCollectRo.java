package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.AssetManagementController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssetManagementControllerImportStopCollectRo extends AssetManagementController {

    @Param
    public String assetPkgNo;

   static {
      DenpendencesProcessor.roDendenpence(AssetManagementControllerImportStopCollectRo.class);
   }
}


