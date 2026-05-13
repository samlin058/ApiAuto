package Data.Ro.IndoArk;

import Data.Apis.IndoArk.CustomerTagController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class CustomerTagControllerDeleteCustomerTagRo extends CustomerTagController {

    @Param
    public String id;
    @Param
    public String customerTagId;
    @Body
    public String reason;
    @Body
    public String loanId;

   static {
      DenpendencesProcessor.roDendenpence(CustomerTagControllerDeleteCustomerTagRo.class);
   }
}


