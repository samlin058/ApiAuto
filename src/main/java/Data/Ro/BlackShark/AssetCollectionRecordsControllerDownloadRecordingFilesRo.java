package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.AssetCollectionRecordsController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssetCollectionRecordsControllerDownloadRecordingFilesRo extends AssetCollectionRecordsController {

    @Param
    public String startDate;
    @Param
    public String assignBatchNo;
    @Param
    public String endDate;

   static {
      DenpendencesProcessor.roDendenpence(AssetCollectionRecordsControllerDownloadRecordingFilesRo.class);
   }
}


