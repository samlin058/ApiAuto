package Data.Ro.IndoArk;

import Data.Apis.IndoArk.LoanInfoController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class LoanInfoControllerAddColorTagRo extends LoanInfoController {

    @Body
    public String color;
    @Body
    public Integer id;

   static {
      DenpendencesProcessor.roDendenpence(LoanInfoControllerAddColorTagRo.class);
   }
}


