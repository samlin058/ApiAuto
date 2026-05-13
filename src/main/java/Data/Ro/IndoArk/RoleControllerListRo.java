package Data.Ro.IndoArk;

import Data.Apis.IndoArk.RoleController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class RoleControllerListRo extends RoleController {

    @Param
    public Integer current;
    @Param
    public Integer pageSize;
    @Param
    public String Code;
    @Param
    public String name;
    @Param
    public String status;

   static {
      DenpendencesProcessor.roDendenpence(RoleControllerListRo.class);
   }
}


