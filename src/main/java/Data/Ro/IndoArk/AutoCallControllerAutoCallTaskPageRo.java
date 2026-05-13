package Data.Ro.IndoArk;

import Data.Apis.IndoArk.AutoCallController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class AutoCallControllerAutoCallTaskPageRo extends AutoCallController {

    @Param
    public String name;
    @Param
    public Number callTemplate;
    @Param
    public String status;
    @Param
    public String startTime;
    @Param
    public String endTime;
    @Param
    public Number current;
    @Param
    public Number pageSize;

   static {
      DenpendencesProcessor.roDendenpence(AutoCallControllerAutoCallTaskPageRo.class);
   }
}


