package Data.Ro.BlackShark;

import Data.Apis.BlackShark.AssetDisposeRevokeController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class AssetDisposeRevokeControllerSaveAssetAutoRevokeJobRo extends AssetDisposeRevokeController {

    @Body
    public List<?> pkgNoList;

   static {
      DenpendencesProcessor.roDendenpence(AssetDisposeRevokeControllerSaveAssetAutoRevokeJobRo.class);
   }
}


