package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.Open;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Util.variable.GenerationHandler;
import Service.Util.Universal.JsonHandler;
import Service.Util.Universal.StringHandler;
import Service.Util.Universal.TimestampHandler;


@lombok.Data
public class OpenLoanApplyServiceRo extends Open {

    static {
        DenpendencesProcessor.roDendenpence(OpenLoanApplyServiceRo.class);
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

    public OpenLoanApplyServiceRo(String channelCode, String creditOrderNo, String loanOrderNo, Double loanAmount, Integer loanPeriod, String bankCard) {
        this.channelCode = channelCode;
        this.reqid = channelCode + TimestampHandler.getCurrentTimestamp();
        if (!StringHandler.isEmpty(creditOrderNo)) {
            this.data = JsonHandler.objectChange2JsonStr(new OpenLoanApplyServiceRo.data(creditOrderNo, loanOrderNo, loanAmount, loanPeriod, bankCard));
        }
        this.timestamp = TimestampHandler.getCurrentTimestamp();
        this.encryptAesKey = "1";
        this.sign = "1";
    }

    public OpenLoanApplyServiceRo(String channelCode, String creditOrderNo, String loanOrderNo, Double loanAmount, Integer loanPeriod, String bankCard, String smsCode) {
        this.channelCode = channelCode;
        this.reqid = channelCode + TimestampHandler.getCurrentTimestamp();
        if (!StringHandler.isEmpty(creditOrderNo)) {
            this.data = JsonHandler.objectChange2JsonStr(new OpenLoanApplyServiceRo.data(creditOrderNo, loanOrderNo, loanAmount, loanPeriod, bankCard, smsCode));
        }
        this.timestamp = TimestampHandler.getCurrentTimestamp();
        this.encryptAesKey = "1";
        this.sign = "1";
    }

    @lombok.Data
    public class data {

        public String creditOrderNo;
        public String loanOrderNo;
        public Double loanAmount;
        public int loanPeriod;
        public String bankCardNum;
        public String loanReason = GenerationHandler.generateRandomInt(1, 7) + "";
        public String smsCode;

        public data(String creditOrderNo, String loanOrderNo, Double loanAmount, Integer loanPeriod, String bankCard, String smsCode) {
            this.creditOrderNo = creditOrderNo;
            this.loanOrderNo = loanOrderNo;
            this.loanAmount = loanAmount;
            this.loanPeriod = loanPeriod;
            this.bankCardNum = bankCard;
            if (StringHandler.isEmpty(smsCode)) {
                this.smsCode = "888888";
            } else {
                this.smsCode = smsCode;
            }
        }

        public data(String creditOrderNo, String loanOrderNo, Double loanAmount, Integer loanPeriod, String bankCard) {
            this.creditOrderNo = creditOrderNo;
            this.loanOrderNo = loanOrderNo;
            this.loanAmount = loanAmount;
            this.loanPeriod = loanPeriod;
            this.bankCardNum = bankCard;
        }
    }
}


