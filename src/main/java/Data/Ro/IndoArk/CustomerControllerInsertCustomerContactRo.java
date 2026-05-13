package Data.Ro.IndoArk;

import Data.Apis.IndoArk.CustomerController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class CustomerControllerInsertCustomerContactRo extends CustomerController {

    @Param
    public String id;
    @Body
    public String name;
    @Body
    public String mobile;
    @Body
    public String remark;
    @Body
    public String source;
    @Body
    public Integer loanId;
    @Body
    public String relation;

   static {
      DenpendencesProcessor.roDendenpence(CustomerControllerInsertCustomerContactRo.class);
   }
}


