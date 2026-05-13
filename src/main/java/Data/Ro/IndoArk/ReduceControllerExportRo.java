package Data.Ro.IndoArk;

import Data.Apis.IndoArk.ReduceController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class ReduceControllerExportRo extends ReduceController {

    @Param
    public String contractNumber;
    @Param
    public String realname;
    @Param
    public Integer reduceApplyId;
    @Param
    public String idno;
    @Param
    public String mobile;
    @Param
    public String beginDate;
    @Param
    public String endDate;
    @Param
    public String result;
    @Param
    public String status;
    @Param
    public Integer applyAgentId;
    @Param
    public Integer superiorAgentId;
    @Param
    public Integer processId;
    @Param
    public String current;
    @Param
    public String pageSize;

   static {
      DenpendencesProcessor.roDendenpence(ReduceControllerExportRo.class);
   }
}


