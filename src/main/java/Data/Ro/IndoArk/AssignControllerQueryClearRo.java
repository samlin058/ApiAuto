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
public class AssignControllerQueryClearRo extends AssignController {

    @Body
    public Integer startLoanAmount;
    @Body
    public List<?> agentIds;
    @Body
    public String batchNo;
    @Body
    public String endCommissionDate;
    @Body
    public String loanStates;
    @Body
    public String contractNumber;
    @Body
    public String idNo;
    @Body
    public String realName;
    @Body
    public String phone;
    @Body
    public List<?> assignStatus;
    @Body
    public Integer endOverdueDays;
    @Body
    public String startCommissionDate;
    @Body
    public Integer endLoanAmount;
    @Body
    public String loanSourceCode;
    @Body
    public Integer startOverdueDays;
    @Body
    public List<?> assistantAgentIds;
    @Body
    public List<?> customerTags;
    @Body
    public String groupCode;

   static {
      DenpendencesProcessor.roDendenpence(AssignControllerQueryClearRo.class);
   }
}


