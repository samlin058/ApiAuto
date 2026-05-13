package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.Open;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class OpenSupportBankServiceRo extends Open {

    @Body
    public Object data;
    @Body
    public String sign;
    @Body
    public String channelCode;
    @Body
    public String reqid;
    @Body
    public String timestamp;
    @Body
    public String encryptAesKey;

   static {
      DenpendencesProcessor.roDendenpence(OpenSupportBankServiceRo.class);
   }
}


