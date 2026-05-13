package Data.Ro.IndoArk;

import Data.Apis.IndoArk.SmsRecordController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class SmsRecordControllerSmsSendRo extends SmsRecordController {

    @Param
    public String mobile;
    @Param
    public String templateName;
    @Param
    public String sendStartTime;
    @Param
    public String sendEndTime;
    @Param
    public Integer sendStatus;
    @Param
    public Integer current;
    @Param
    public Integer pageSize;
    @Param
    public String batchNumber;
    @Param
    public String contractNumber;
    @Param
    public Integer batchId;

   static {
      DenpendencesProcessor.roDendenpence(SmsRecordControllerSmsSendRo.class);
   }
}


