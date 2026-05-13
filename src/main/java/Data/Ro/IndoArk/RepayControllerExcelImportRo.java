package Data.Ro.IndoArk;

import Data.Apis.IndoArk.RepayController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class RepayControllerExcelImportRo extends RepayController {

    @FileBody
    public String file;
    @Body
    public Integer loanSourceId;

   static {
      DenpendencesProcessor.roDendenpence(RepayControllerExcelImportRo.class);
   }
}


