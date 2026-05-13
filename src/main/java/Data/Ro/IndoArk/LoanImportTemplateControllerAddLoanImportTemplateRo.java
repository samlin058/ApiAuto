package Data.Ro.IndoArk;

import Data.Apis.IndoArk.LoanImportTemplateController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class LoanImportTemplateControllerAddLoanImportTemplateRo extends LoanImportTemplateController {

    @Param
    public String name;
    @Param
    public String createdTimeStart;
    @Param
    public String createdTimeEnd;
    @Param
    public String createdBy;
    @Param
    public Integer active;
    @Param
    public Integer current;
    @Param
    public Integer pageSize;

   static {
      DenpendencesProcessor.roDendenpence(LoanImportTemplateControllerAddLoanImportTemplateRo.class);
   }
}


