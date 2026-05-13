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
public class LoanBatchControllerDeleteRo extends LoanBatchController {

    @Body
    public Integer importBatchId;

   static {
      DenpendencesProcessor.roDendenpence(LoanBatchControllerDeleteRo.class);
   }
}


