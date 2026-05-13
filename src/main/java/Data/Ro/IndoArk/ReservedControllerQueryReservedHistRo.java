package Data.Ro.IndoArk;

import Data.Apis.IndoArk.ReservedController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class ReservedControllerQueryReservedHistRo extends ReservedController {

    @Param
    public String customerName;
    @Param
    public String approveStatus;
    @Param
    public String expiredDate;
    @Param
    public Integer superiorAgentId;
    @Param
    public Integer caseAgentId;
    @Param
    public String reservedResult;
    @Param
    public String beginDate;
    @Param
    public String endDate;
    @Param
    public Integer pageSize;
    @Param
    public Integer current;

   static {
      DenpendencesProcessor.roDendenpence(ReservedControllerQueryReservedHistRo.class);
   }
}


