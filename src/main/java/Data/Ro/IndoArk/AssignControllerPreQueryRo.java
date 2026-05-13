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
public class AssignControllerPreQueryRo extends AssignController {

    @Param
    public String realName;
    @Param
    public String contractNumber;
    @Param
    public String idNo;
    @Param
    public String phone;
    @Param
    public String groupCode;
    @Param
    public String[] agentIds;
    @Param
    public String[] assistantAgentIds;
    @Param
    public String batchNo;
    @Param
    public String startCommissionDate;
    @Param
    public String endCommissionDate;
    @Param
    public Number startOverdueDays;
    @Param
    public Number endOverdueDays;
    @Param
    public Number startLoanAmount;
    @Param
    public Number endLoanAmount;
    @Param
    public String[] loanStates;
    @Param
    public String loanSourceCode;
    @Param
    public String[] assignStatus;
    @Param
    public String[] customerTags;

   static {
      DenpendencesProcessor.roDendenpence(AssignControllerPreQueryRo.class);
   }
}


