package Data.Ro.IndoArk;

import Data.Apis.IndoArk.PrepareOperationController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class PrepareOperationControllerRecordDeleteRo extends PrepareOperationController {

    @Param
    public String recordId;

   static {
      DenpendencesProcessor.roDendenpence(PrepareOperationControllerRecordDeleteRo.class);
   }
}


