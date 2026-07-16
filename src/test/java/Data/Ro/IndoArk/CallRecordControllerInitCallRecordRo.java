package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.CallRecordController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class CallRecordControllerInitCallRecordRo extends CallRecordController {

    @Body
    public Integer customerContactId;
    @Body
    public String relationValue;
    @Body
    public String loanId;
    @Body
    public String relation;

   static {
      DenpendencesProcessor.roDendenpence(CallRecordControllerInitCallRecordRo.class);
   }
}


