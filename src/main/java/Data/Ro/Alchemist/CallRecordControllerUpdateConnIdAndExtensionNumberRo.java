package Data.Ro.Alchemist;

import Data.Apis.Alchemist.CallRecordController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class CallRecordControllerUpdateConnIdAndExtensionNumberRo extends CallRecordController {

    @Param
    public String callRecordId;
    @Body
    public String extensionNumber;
    @Body
    public String connId;

   static {
      DenpendencesProcessor.roDendenpence(CallRecordControllerUpdateConnIdAndExtensionNumberRo.class);
   }
}


