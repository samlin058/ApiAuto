package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.AssetController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class AssetControllerQueryAssetByQlRo extends AssetController {

    @Body
    public String jobId;
    @Body
    public String assetId;
    @Body
    public String query;

   static {
      DenpendencesProcessor.roDendenpence(AssetControllerQueryAssetByQlRo.class);
   }
}


