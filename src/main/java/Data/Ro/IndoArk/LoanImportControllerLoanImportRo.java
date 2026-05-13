package Data.Ro.IndoArk;

import Data.Apis.IndoArk.LoanImportController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class LoanImportControllerLoanImportRo extends LoanImportController {

    @FileBody
    public String file;
    @Body
    public Integer importTemplateId;
    @Body
    public String importBatchId;

   static {
      DenpendencesProcessor.roDendenpence(LoanImportControllerLoanImportRo.class);
   }
}


