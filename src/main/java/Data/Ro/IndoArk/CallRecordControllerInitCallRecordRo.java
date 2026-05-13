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
public class CallRecordControllerInitCallRecordRo extends CallRecordController {

    @Body
    public Integer customerContactId;
    @Body
    public String relationValue;
    @Body
    public String loanId;
    @Body
    public String relation;

   static {
      DenpendencesProcessor.roDendenpence(CallRecordControllerInitCallRecordRo.class);
   }
}


