package Data.Ro.BlackShark;

import Data.Apis.BlackShark.QualityInspectionController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class QualityInspectionControllerResultRo extends QualityInspectionController {

    @Body
    public String checkReceiptId;
    @Body
    public String checkRequestId;
    @Body
    public String bizId;
    @Body
    public String voiceDuration;
    @Body
    public List<?> checkPoints;
    @Body
    public List<?> voiceText;

   static {
      DenpendencesProcessor.roDendenpence(QualityInspectionControllerResultRo.class);
   }
}


