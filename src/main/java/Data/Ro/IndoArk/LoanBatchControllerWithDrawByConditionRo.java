package Data.Ro.IndoArk;

import Data.Apis.IndoArk.LoanBatchController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class LoanBatchControllerWithDrawByConditionRo extends LoanBatchController {

    @Body
    public String commissionOrg;
    @Body
    public String withdrawDateEnd;
    @Body
    public String commissionDateStart;
    @Body
    public String withdrawDateStart;
    @Body
    public String commissionDateEnd;
    @Body
    public Integer loanSourceId;
    @Body
    public String batchNumber;

   static {
      DenpendencesProcessor.roDendenpence(LoanBatchControllerWithDrawByConditionRo.class);
   }
}


