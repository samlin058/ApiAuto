package Data.Ro.IndoArk;

import Data.Apis.IndoArk.ReduceController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class ReduceControllerApplyRo extends ReduceController {

    @Body
    public String reduceContentType;
    @Body
    public Number remainingAmount;
    @Body
    public Integer totalInstallments;
    @Body
    public String attachments;
    @Body
    public Number reduceAmount;
    @Body
    public String reduceReasonType;
    @Body
    public String reduceReason;
    @Body
    public Integer loanId;

   static {
      DenpendencesProcessor.roDendenpence(ReduceControllerApplyRo.class);
   }
}


