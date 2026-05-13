package Data.Ro.IndoArk;

import Data.Apis.IndoArk.MultipleDebtController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class MultipleDebtControllerGetApplicationRo extends MultipleDebtController {

    @Param
    public String loanId;

   static {
      DenpendencesProcessor.roDendenpence(MultipleDebtControllerGetApplicationRo.class);
   }
}


