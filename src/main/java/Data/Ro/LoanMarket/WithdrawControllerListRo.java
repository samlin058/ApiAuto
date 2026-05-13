package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.WithdrawController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationLibrary.Header;
import Service.Annotations.AnnotationLibrary.JsonArray;
import Service.Annotations.AnnotationLibrary.PathParam;
import java.util.List;


@lombok.Data
public class WithdrawControllerListRo extends WithdrawController {

   static {
      DenpendencesProcessor.roDendenpence(WithdrawControllerListRo.class);
   }

    @Param
        public String userId;
    @Param
        public String userName;
    @Param
        public String idNo;
    @Param
        public String mobile;
    @Param
        public String channelCode;
    @Param
        public String partnerCode;
    @Param
        public String status;
    @Param
        public String withdrawalStartTime;
    @Param
        public String withdrawalEndTime;
    @Param
        public String channelRequestId;
    @Param
        public String orderNo;
    @Param
        public String thirdOrderNo;
    @Param
        public String current;
    @Param
        public String pageSize;


}


