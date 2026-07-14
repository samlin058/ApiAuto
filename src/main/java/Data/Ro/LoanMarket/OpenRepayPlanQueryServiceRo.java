package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.Open;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Util.Universal.JsonHandler;
import Service.Util.Universal.StringHandler;
import Service.Util.Universal.TimestampHandler;


@lombok.Data
public class OpenRepayPlanQueryServiceRo extends Open {

    static {
        DenpendencesProcessor.roDendenpence(OpenRepayPlanQueryServiceRo.class);
    }

    @Body
    public Object data;
    @Body
    public String sign;
    @Body
    public String channelCode;
    @Body
    public String reqid;
    @Body
    public Long timestamp;
    @Body
    public String encryptAesKey;


    public OpenRepayPlanQueryServiceRo(String channelCode, String loanOrderNo) {
        this.channelCode = channelCode;
        this.reqid = channelCode + TimestampHandler.getCurrentTimestamp();
        if (!StringHandler.isEmpty(loanOrderNo)) {
            this.data = JsonHandler.objectChange2JsonStr(new OpenRepayPlanQueryServiceRo.data(loanOrderNo));
        }
        this.timestamp = TimestampHandler.getCurrentTimestamp();
        this.encryptAesKey = "1";
        this.sign = "1";
    }

    @lombok.Data
    public class data {

        public String loanOrderNo;
        public String thirdLoanOrderNo;

        public data(String loanOrderNo) {
            this.loanOrderNo = loanOrderNo;
            this.thirdLoanOrderNo = thirdLoanOrderNo;
        }
    }
}


