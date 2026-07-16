package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.AssignController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class AssignControllerPreAssignByGroupRo extends AssignController {

    @Param
    public String[] loanIds;
    @Param
    public String groupId;
    @Param
    public String assigned;
    @Param
    public String reserved;
    @Body
    public String realName;
    @Body
    public String agentIds;
    @Body
    public String phone;
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
      DenpendencesProcessor.roDendenpence(AssignControllerPreAssignByGroupRo.class);
   }
}


