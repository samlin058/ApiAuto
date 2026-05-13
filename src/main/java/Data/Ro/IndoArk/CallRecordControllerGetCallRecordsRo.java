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
public class CallRecordControllerGetCallRecordsRo extends CallRecordController {

    @Param
    public String loanId;
    @Param
    public String current;
    @Param
    public String pageSize;
    @Param
    public Integer agentId;
    @Param
    public String phone;
    @Param
    public String name;
    @Param
    public String callType;
    @Param
    public String connectionStatus;
    @Param
    public String recordDateBegin;
    @Param
    public String recordDateEnd;

   static {
      DenpendencesProcessor.roDendenpence(CallRecordControllerGetCallRecordsRo.class);
   }
}


