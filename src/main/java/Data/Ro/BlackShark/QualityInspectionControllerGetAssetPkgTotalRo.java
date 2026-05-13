package Data.Ro.BlackShark;

import Data.Apis.BlackShark.QualityInspectionController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class QualityInspectionControllerGetAssetPkgTotalRo extends QualityInspectionController {

    @Param
    public String recordType;
    @Param
    public String startRecordDate;
    @Param
    public String endRecordDate;

   static {
      DenpendencesProcessor.roDendenpence(QualityInspectionControllerGetAssetPkgTotalRo.class);
   }
}


