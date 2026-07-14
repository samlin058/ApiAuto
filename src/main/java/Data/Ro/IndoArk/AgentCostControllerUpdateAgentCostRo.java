package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.AgentCostController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class AgentCostControllerUpdateAgentCostRo extends AgentCostController {

    @Param
    public String id;
    @Body
    public Integer amount;
    @Body
    public Number weight;

   static {
      DenpendencesProcessor.roDendenpence(AgentCostControllerUpdateAgentCostRo.class);
   }
}


