package Data.Ro.BlackShark;

import Data.Apis.BlackShark.QualityInspectionController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class QualityInspectionControllerGetQualityInspectionListRo extends QualityInspectionController {

    @Param
    public String current;
    @Param
    public String disposerCode;
    @Param
    public String endDate;
    @Param
    public String pageSize;
    @Param
    public String startDate;
    @Param
    public String sourceType;

   static {
      DenpendencesProcessor.roDendenpence(QualityInspectionControllerGetQualityInspectionListRo.class);
   }
}


