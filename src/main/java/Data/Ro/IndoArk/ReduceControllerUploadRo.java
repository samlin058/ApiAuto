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
public class ReduceControllerUploadRo extends ReduceController {

    @FileBody
    public String file;

   static {
      DenpendencesProcessor.roDendenpence(ReduceControllerUploadRo.class);
   }
}


