package Data.Ro.BlackShark;

import Data.Apis.BlackShark.AssetDisposeBatchController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class AssetDisposeBatchControllerImportAssignmentRo extends AssetDisposeBatchController {

    @FileBody
    public String file;
    @Body
    public String assetPkgNo;
    @Body
    public String preAssetPkgId;

   static {
      DenpendencesProcessor.roDendenpence(AssetDisposeBatchControllerImportAssignmentRo.class);
   }
}


