package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.Open;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Util.Universal.JsonHandler;
import Service.Util.Universal.StringHandler;
import Service.Util.Universal.TimestampHandler;


@lombok.Data
public class OpenBindCardConfirmServiceRo extends Open {

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


    static {
        DenpendencesProcessor.roDendenpence(OpenBindCardConfirmServiceRo.class);
    }

    public OpenBindCardConfirmServiceRo(String channelCode, String creditOrderNo, String bankCardNo, String phoneNumber, String idNo, String name) {
        this.channelCode = channelCode;
        this.reqid = channelCode + TimestampHandler.getCurrentTimestamp();
        if (!StringHandler.isEmpty(creditOrderNo)) {
            this.data = JsonHandler.objectChange2JsonStr(new OpenBindCardConfirmServiceRo.data(channelCode, creditOrderNo, bankCardNo, phoneNumber, idNo, name));
        }
        this.timestamp = TimestampHandler.getCurrentTimestamp();
        this.encryptAesKey = "1";
        this.sign = "1";
    }

    @lombok.Data
    public class data {
        public String creditOrderNo;
        public String requestNo;
        public String bankCardNumber;
        public String phoneNumber;
        public String idCardNumber;
        public String name;
        public String verifyCode;

        public data(String channelCode, String creditOrderNo, String bankCardNumber, String phoneNumber, String idCardNumber, String name) {
            this.creditOrderNo = creditOrderNo;
            this.requestNo = channelCode + TimestampHandler.getCurrentTimestamp();
            this.bankCardNumber = bankCardNumber;
            this.phoneNumber = phoneNumber;
            this.idCardNumber = idCardNumber;
            this.name = name;
            if ("niwodai".equalsIgnoreCase(channelCode)) {
                this.verifyCode = "111111";
            } else {
                this.verifyCode = "888888";
            }
        }

        public data(String channelCode, String creditOrderNo, String bankCardNumber, String phoneNumber, String idCardNumber, String name, String verifyCode) {
            this.creditOrderNo = creditOrderNo;
            this.requestNo = channelCode + TimestampHandler.getCurrentTimestamp();
            this.bankCardNumber = bankCardNumber;
            this.phoneNumber = phoneNumber;
            this.idCardNumber = idCardNumber;
            this.name = name;
            if ("niwodai".equalsIgnoreCase(channelCode)) {
                this.verifyCode = "111111";
            } else {
                this.verifyCode = verifyCode;
            }
        }
    }


}


