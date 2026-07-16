package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.AssetInteractionFileController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class AssetInteractionFileControllerImportCallRecordRo extends AssetInteractionFileController {

    @FileBody
    public String file;
    @Body
    public String assetPkgNo;
    @Body
    public String id;

   static {
      DenpendencesProcessor.roDendenpence(AssetInteractionFileControllerImportCallRecordRo.class);
   }
}


