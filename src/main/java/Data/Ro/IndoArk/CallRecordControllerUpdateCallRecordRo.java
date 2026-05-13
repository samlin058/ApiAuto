package Data.Ro.IndoArk;

import Data.Apis.IndoArk.CallRecordController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class CallRecordControllerUpdateCallRecordRo extends CallRecordController {

    @Param
    public String callRecordId;
    @Body
    public String result;
    @Body
    public String conclusion;
    @Body
    public String overdueReason;
    @Body
    public Number promisedPaymentAmount;
    @Body
    public String promisedPaymentDate;
    @Body
    public String connectionStatus;
    @Body
    public String recheckDate;
    @Body
    public String remark;

   static {
      DenpendencesProcessor.roDendenpence(CallRecordControllerUpdateCallRecordRo.class);
   }
}


