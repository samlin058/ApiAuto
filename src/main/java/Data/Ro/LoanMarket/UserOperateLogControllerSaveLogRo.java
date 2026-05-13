package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.UserOperateLogController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class UserOperateLogControllerSaveLogRo extends UserOperateLogController {

   static {
      DenpendencesProcessor.roDendenpence(UserOperateLogControllerSaveLogRo.class);
   }

    @Body
    public String channel;
    @Body
    public String mobile;
    @Body
    public String page;
    @Body
    public String type;
    @Body
    public String info;


}


