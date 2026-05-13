package Data.Ro.BlackShark;

import Data.Apis.BlackShark.AssetQueryOpenController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class AssetQueryOpenControllerQueryAssetRo extends AssetQueryOpenController {

    @Body
    public List<?> types;
    @Body
    public String assetId;
    @Body
    public String assignBatchNo;

   static {
      DenpendencesProcessor.roDendenpence(AssetQueryOpenControllerQueryAssetRo.class);
   }
}


