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
public class AssignControllerManualRo extends AssignController {

    @Body
    public Integer agentId;
    @Body
    public List<?> loanIdList;
    @Body
    public String groupId;

   static {
      DenpendencesProcessor.roDendenpence(AssignControllerManualRo.class);
   }
}


