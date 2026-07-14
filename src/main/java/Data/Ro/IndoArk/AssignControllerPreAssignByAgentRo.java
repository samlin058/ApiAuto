package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.AssignController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class AssignControllerPreAssignByAgentRo extends AssignController {

    @Body
    public String realName;
    @Body
    public String agentIds;
    @Body
    public String phone;
    @Body
    public String groupId;
    @Body
    public String groupIds;
    @Body
    public String contractNumber;
    @Body
    public String assistantAgentIds;
    @Body
    public String idNo;
    @Body
    public String idNoLastFour;

   static {
      DenpendencesProcessor.roDendenpence(AssignControllerPreAssignByAgentRo.class);
   }
}


