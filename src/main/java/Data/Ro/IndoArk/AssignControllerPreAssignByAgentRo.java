package Data.Ro.IndoArk;

import Data.Apis.IndoArk.AssignController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


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


