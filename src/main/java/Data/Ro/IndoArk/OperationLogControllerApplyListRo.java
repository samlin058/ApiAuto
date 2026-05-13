package Data.Ro.IndoArk;

import Data.Apis.IndoArk.OperationLogController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class OperationLogControllerApplyListRo extends OperationLogController {

    @Param
    public String type;
    @Param
    public String operator;
    @Param
    public String beginDate;
    @Param
    public String endDate;
    @Param
    public Integer loanId;
    @Param
    public String moduleCode;
    @Param
    public String[] typeList;
    @Param
    public String customerId;
    @Param
    public Integer current;
    @Param
    public String pageSize;

   static {
      DenpendencesProcessor.roDendenpence(OperationLogControllerApplyListRo.class);
   }
}


