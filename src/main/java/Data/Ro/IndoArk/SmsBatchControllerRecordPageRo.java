package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.SmsBatchController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class SmsBatchControllerRecordPageRo extends SmsBatchController {

    @Param
    public Integer current;
    @Param
    public Integer pageSize;
    @Param
    public Integer templateId;
    @Param
    public String sendStartTime;
    @Param
    public String sendEndTime;

   static {
      DenpendencesProcessor.roDendenpence(SmsBatchControllerRecordPageRo.class);
   }
}


