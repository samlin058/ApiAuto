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
public class CallRecordControllerLinkupCallRecordCallbackRo extends CallRecordController {

    @Body
    public String batchNo;
    @Body
    public String agentId;
    @Body
    public String userData;
    @Body
    public String prefix;
    @Body
    public String mobile;
    @Body
    public Integer resultCode;
    @Body
    public String type;
    @Body
    public Integer attempt;
    @Body
    public String resultMsg;
    @Body
    public Integer duration;
    @Body
    public String inputMenu;
    @Body
    public Integer round;
    @Body
    public Integer callStatus;
    @Body
    public String connId;
    @Body
    public String startTime;
    @Body
    public String endTime;
    @Body
    public String campaignKey;
    @Body
    public String callTime;

   static {
      DenpendencesProcessor.roDendenpence(CallRecordControllerLinkupCallRecordCallbackRo.class);
   }
}


