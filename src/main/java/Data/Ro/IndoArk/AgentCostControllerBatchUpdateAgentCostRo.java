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


