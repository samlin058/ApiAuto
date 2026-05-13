package Data.Ro.IndoArk;

import Data.Apis.IndoArk.AgentCostController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


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


