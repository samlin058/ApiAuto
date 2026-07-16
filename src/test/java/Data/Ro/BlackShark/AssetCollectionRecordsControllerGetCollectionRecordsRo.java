package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.AssetCollectionRecordsController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssetCollectionRecordsControllerGetCollectionRecordsRo extends AssetCollectionRecordsController {

    @Param
    public String current;
    @Param
    public String pageSize;
    @Param
    public String requisitionNumber;

   static {
      DenpendencesProcessor.roDendenpence(AssetCollectionRecordsControllerGetCollectionRecordsRo.class);
   }
}


