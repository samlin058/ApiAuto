package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.AgentVoiceController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AgentVoiceControllerExportRo extends AgentVoiceController {

    @Param
    public String startDate;
    @Param
    public String endDate;
    @Param
    public String agent;
    @Param
    public String agentGroup;

   static {
      DenpendencesProcessor.roDendenpence(AgentVoiceControllerExportRo.class);
   }
}


