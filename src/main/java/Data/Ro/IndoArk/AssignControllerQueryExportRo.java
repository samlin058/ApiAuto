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
public class AssignControllerQueryExportRo extends AssignController {

    @Param
    public String contractNumber	;
    @Param
    public String idNo;
    @Param
    public String realName;
    @Param
    public String phone;
    @Param
    public String agentIds	;
    @Param
    public String assistantAgentIds;
    @Param
    public String[] customerTags;

   static {
      DenpendencesProcessor.roDendenpence(AssignControllerQueryExportRo.class);
   }
}


