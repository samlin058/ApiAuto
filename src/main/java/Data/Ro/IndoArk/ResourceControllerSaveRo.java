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
public class ResourceControllerSaveRo extends ResourceController {

    @Body
    public String Code;
    @Body
    public String resourceUrl;
    @Body
    public String parentCode;
    @Body
    public String resourceExt;
    @Body
    public String name;
    @Body
    public String icon;
    @Body
    public String comment;
    @Body
    public Integer id;
    @Body
    public String appCode;
    @Body
    public Integer sort;
    @Body
    public Integer status;
    @Body
    public Integer resourceType;

   static {
      DenpendencesProcessor.roDendenpence(ResourceControllerSaveRo.class);
   }
}


