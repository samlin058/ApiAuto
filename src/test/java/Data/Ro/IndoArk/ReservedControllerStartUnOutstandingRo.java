package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.ReservedController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class ReservedControllerStartUnOutstandingRo extends ReservedController {


    @Body
    public Integer expiredDate;
    @Body
    public Integer assignmentId;
    @Body
    public String remarks;
    @Body
    public String loanId;

   static {
      DenpendencesProcessor.roDendenpence(ReservedControllerStartUnOutstandingRo.class);
   }
}


