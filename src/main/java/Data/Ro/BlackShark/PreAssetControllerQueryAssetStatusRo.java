package Data.Ro.BlackShark;

import Data.Apis.BlackShark.PreAssetController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class PreAssetControllerQueryAssetStatusRo extends PreAssetController {

    @Param
    public String assetPackageId;

   static {
      DenpendencesProcessor.roDendenpence(PreAssetControllerQueryAssetStatusRo.class);
   }

    public PreAssetControllerQueryAssetStatusRo(String assetPackageId) {
        this.assetPackageId = assetPackageId;
    }
}


