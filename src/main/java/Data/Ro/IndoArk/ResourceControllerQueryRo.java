package Data.Ro.IndoArk;

import Data.Apis.IndoArk.ResourceController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class ResourceControllerQueryRo extends ResourceController {

    @Param
    public String appCode;
    @Param
    public String name;
    @Param
    public String Code;
    @Param
    public String status;
    @Param
    public String level;
    @Param
    public String resourceType;
    @Param
    public String parentCode;
    @Param
    public String limitCodes;

   static {
      DenpendencesProcessor.roDendenpence(ResourceControllerQueryRo.class);
   }
}


