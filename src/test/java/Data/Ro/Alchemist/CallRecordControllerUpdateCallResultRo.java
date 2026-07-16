package Data.Ro.Alchemist;

import Data.ApisLayer.Alchemist.CallRecordController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class CallRecordControllerUpdateCallResultRo extends CallRecordController {

    @Param
    public String callRecordId;
    @Body
    public String result;
    @Body
    public String callType;

   static {
      DenpendencesProcessor.roDendenpence(CallRecordControllerUpdateCallResultRo.class);
   }
}


