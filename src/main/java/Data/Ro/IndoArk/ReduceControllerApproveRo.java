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
public class ReduceControllerApproveRo extends ReduceController {

    @Body
    public Integer reduceId;
    @Body
    public String status;

   static {
      DenpendencesProcessor.roDendenpence(ReduceControllerApproveRo.class);
   }
}


