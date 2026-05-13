package Data.Ro.IndoArk;

import Data.Apis.IndoArk.GateWayController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class GateWayControllerIsChildRo extends GateWayController {

    @Param
    public String appCode;
    @Param
    public String parent;
    @Param
    public String child;

   static {
      DenpendencesProcessor.roDendenpence(GateWayControllerIsChildRo.class);
   }
}


