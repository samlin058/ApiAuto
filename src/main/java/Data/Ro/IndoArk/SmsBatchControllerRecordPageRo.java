package Data.Ro.IndoArk;

import Data.Apis.IndoArk.SmsBatchController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class SmsBatchControllerRecordPageRo extends SmsBatchController {

    @Param
    public Integer current;
    @Param
    public Integer pageSize;
    @Param
    public Integer templateId;
    @Param
    public String sendStartTime;
    @Param
    public String sendEndTime;

   static {
      DenpendencesProcessor.roDendenpence(SmsBatchControllerRecordPageRo.class);
   }
}


