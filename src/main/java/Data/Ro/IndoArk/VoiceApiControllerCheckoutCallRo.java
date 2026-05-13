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
public class VoiceApiControllerCheckoutCallRo extends VoiceApiController {

    @Body
    public Integer customerContactId;
    @Body
    public String callType;

   static {
      DenpendencesProcessor.roDendenpence(VoiceApiControllerCheckoutCallRo.class);
   }
}


