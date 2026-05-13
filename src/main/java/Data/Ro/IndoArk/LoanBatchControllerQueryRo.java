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
public class LoanBatchControllerQueryRo extends LoanBatchController {

    @Param
    public Integer current;
    @Param
    public Integer pageSize;
    @Param
    public String batchNumber;
    @Param
    public String commissionDateStart;
    @Param
    public String commissionDateEnd;
    @Param
    public String commissionOrg;
    @Param
    public String withdrawDateStart;
    @Param
    public String withdrawDateEnd;
    @Param
    public String loanSourceId;
    @Param
    public String totalCase;

   static {
      DenpendencesProcessor.roDendenpence(LoanBatchControllerQueryRo.class);
   }
}


