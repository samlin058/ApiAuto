package Data.Ro.IndoArk;

import Data.Apis.IndoArk.UserClockInController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class UserClockInControllerExportRo extends UserClockInController {

    @Param
    public String checkInTimeStart;
    @Param
    public String checkInTimeEnd;
    @Param
    public Number clockInDurationStart;
    @Param
    public Number clockInDurationEnd;
    @Param
    public String[] userIds;
    @Param
    public Number current;
    @Param
    public Number pageSize;

   static {
      DenpendencesProcessor.roDendenpence(UserClockInControllerExportRo.class);
   }
}


