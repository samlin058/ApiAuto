package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.ComplaintController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class ComplaintControllerComplaintTrendRo extends ComplaintController {

    @Param
    public String[] disposerId;

   static {
      DenpendencesProcessor.roDendenpence(ComplaintControllerComplaintTrendRo.class);
   }
}


