package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.WithdrawController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


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


