package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.AgentCostController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class AgentCostControllerBatchUpdateAgentCostRo extends AgentCostController {

    @Body
    public Integer amount;
    @Body
    public String month;
    @Body
    public Number weight;

   static {
      DenpendencesProcessor.roDendenpence(AgentCostControllerBatchUpdateAgentCostRo.class);
   }
}


