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
public class AssignControllerQueryRo extends AssignController {

    @Param
    public String current;
    @Param
    public String pageSize;
    @Param
    public String contractNumber;
    @Param
    public String idNo;
    @Param
    public String realName;
    @Param
    public String phone;
    @Param
    public String[] agentIds;
    @Param
    public String[] assistantAgentIds;
    @Param
    public String assignStatus;
    @Param
    public String unAssign;
    @Param
    public String groupCode;
    @Param
    public Integer startOverdueDays;
    @Param
    public Integer endOverdueDays;
    @Param
    public Integer startLoanAmount;
    @Param
    public Integer endLoanAmount;
    @Param
    public Integer startCommissionDate;
    @Param
    public Integer endCommissionDate;
    @Param
    public String loanSourceCode;
    @Param
    public String loanStates;
    @Param
    public String[] customerTags;

   static {
      DenpendencesProcessor.roDendenpence(AssignControllerQueryRo.class);
   }
}


