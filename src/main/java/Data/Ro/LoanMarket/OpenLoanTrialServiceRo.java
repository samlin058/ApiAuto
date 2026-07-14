package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.Open;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Util.Universal.JsonHandler;
import Service.Util.Universal.StringHandler;
import Service.Util.Universal.TimestampHandler;


@lombok.Data
public class OpenLoanTrialServiceRo extends Open {

    static {
        DenpendencesProcessor.roDendenpence(OpenLoanTrialServiceRo.class);
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

    public OpenLoanTrialServiceRo(String channelCode, String creditOrderNo, Double loanAmount, Integer loanPeriod) {
        this.channelCode = channelCode;
        this.reqid = channelCode + TimestampHandler.getCurrentTimestamp();
        if (!StringHandler.isEmpty(creditOrderNo)) {
            this.data = JsonHandler.objectChange2JsonStr(new OpenLoanTrialServiceRo.data(creditOrderNo, loanAmount, loanPeriod));
        }
        this.timestamp = TimestampHandler.getCurrentTimestamp();
        this.encryptAesKey = "1";
        this.sign = "1";
    }

    @lombok.Data
    public class data {
        public String creditOrderNo;
        public Double loanAmount;         // 新增字段（金额）
        public Integer loanPeriod;         // 新增字段（期限）

        public data(String creditOrderNo, Double loanAmount, Integer loanPeriod) {
            this.creditOrderNo = creditOrderNo;
            this.loanAmount = loanAmount;
            this.loanPeriod = loanPeriod;
        }
    }

}


