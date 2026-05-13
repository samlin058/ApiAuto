package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.TestKaniuController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class TestKaniuControllerKaNiuRepayConfirmServiceRo extends TestKaniuController {

    @Body
    public String orderId;
    @Body
    public String repayId;
    @Body
    public String userId;
    @Body
    public String loanId;
    @Body
    public String formH5;
    @Body
    public String channelCode;

   static {
      DenpendencesProcessor.roDendenpence(TestKaniuControllerKaNiuRepayConfirmServiceRo.class);
   }
}


