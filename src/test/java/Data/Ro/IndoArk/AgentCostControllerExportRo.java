package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.AgentCostController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AgentCostControllerExportRo extends AgentCostController {

    @Param
    public String month;
    @Param
    public String account;

   static {
      DenpendencesProcessor.roDendenpence(AgentCostControllerExportRo.class);
   }
}


