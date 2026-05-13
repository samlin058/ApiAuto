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
public class LoanImportTemplateControllerUpdateLoanImportTemplateFieldRo extends LoanImportTemplateController {

    @Param
    public Integer id;
    @Param
    public String fieldId;

   static {
      DenpendencesProcessor.roDendenpence(LoanImportTemplateControllerUpdateLoanImportTemplateFieldRo.class);
   }
}


