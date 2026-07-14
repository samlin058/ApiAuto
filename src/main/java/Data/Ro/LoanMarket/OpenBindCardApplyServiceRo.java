package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.Open;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Util.Universal.JsonHandler;
import Service.Util.Universal.StringHandler;
import Service.Util.Universal.TimestampHandler;
import lombok.Data;


@lombok.Data
public class OpenBindCardApplyServiceRo extends Open {

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

    public OpenBindCardApplyServiceRo(String channelCode, String creditOrderNo, String bankCardNo, String phoneNumber, String idno, String name ) {
        this.channelCode = channelCode;
        this.reqid = channelCode + TimestampHandler.getCurrentTimestamp();
        if (!StringHandler.isEmpty(creditOrderNo)) {
            this.data = JsonHandler.objectChange2JsonStr(new OpenBindCardApplyServiceRo.data(channelCode, creditOrderNo, bankCardNo, phoneNumber, idno, name));
        }
        this.timestamp = TimestampHandler.getCurrentTimestamp();
        this.encryptAesKey = "1";
        this.sign = "1";
    }

    static {
        DenpendencesProcessor.roDendenpence(OpenBindCardApplyServiceRo.class);
    }

    @Data
    public class data {
        public String creditOrderNo;
        public String requestNo;
        public String bankCardNumber;
        public String phoneNumber;
        public String idCardNumber;
        public String name;

        /**
         * @param channelCode    渠道号
         * @param creditOrderNo  授信单号
         * @param bankCardNumber 银行卡号
         * @param phoneNumber    手机号
         * @param idCardNumber   身份证号
         * @param name           姓名
         */
        public data(String channelCode, String creditOrderNo, String bankCardNumber, String phoneNumber, String idCardNumber, String name) {
            this.creditOrderNo = creditOrderNo;
            this.requestNo = channelCode + TimestampHandler.getCurrentTimestamp();
            this.bankCardNumber = bankCardNumber;
            this.phoneNumber = phoneNumber;
            this.idCardNumber = idCardNumber;
            this.name = name;
        }
    }
}


