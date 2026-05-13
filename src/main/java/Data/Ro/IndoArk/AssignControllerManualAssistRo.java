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
public class AssignControllerManualAssistRo extends AssignController {

    @Body
    public Integer agentId;
    @Body
    public List<?> loanIdList;

   static {
      DenpendencesProcessor.roDendenpence(AssignControllerManualAssistRo.class);
   }
}


