package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.H5LinkController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class H5LinkControllerH5Ro extends H5LinkController {

   static {
      DenpendencesProcessor.roDendenpence(H5LinkControllerH5Ro.class);
   }

    @Param
    public String channel;
    @Param
    public String partnerCode;
    @Param
    public String type;


}


