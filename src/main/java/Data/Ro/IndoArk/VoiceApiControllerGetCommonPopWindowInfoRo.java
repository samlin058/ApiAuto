package Data.Ro.IndoArk;

import Data.Apis.IndoArk.VoiceApiController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class VoiceApiControllerGetCommonPopWindowInfoRo extends VoiceApiController {

    @Param
    public Integer loanId;
    @Param
    public String maskId;

   static {
      DenpendencesProcessor.roDendenpence(VoiceApiControllerGetCommonPopWindowInfoRo.class);
   }
}


