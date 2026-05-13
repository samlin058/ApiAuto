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
public class LoanImportControllerGetLoanImportFailFieldDownloadRo extends LoanImportController {

    @Param
    public String importBatchId;
    @Param
    public String importRecordId;

   static {
      DenpendencesProcessor.roDendenpence(LoanImportControllerGetLoanImportFailFieldDownloadRo.class);
   }
}


