package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.Open;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Util.Universal.JsonHandler;
import Service.Util.Universal.StringHandler;
import Service.Util.Universal.TimestampHandler;


@lombok.Data
public class OpenCreditResultQueryServiceRo extends Open {

    @Body
    public String data;
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
        DenpendencesProcessor.roDendenpence(OpenCreditResultQueryServiceRo.class);
    }

    public OpenCreditResultQueryServiceRo(String channelCode,String CreditOrderNo) {
        this.channelCode = channelCode;
        this.reqid = channelCode + TimestampHandler.getCurrentTimestamp();
        if (!StringHandler.isEmpty(CreditOrderNo)) {
            this.data = JsonHandler.objectChange2JsonStr(new OpenCreditResultQueryServiceRo.data(CreditOrderNo));
        }
        this.timestamp = TimestampHandler.getCurrentTimestamp();
        this.encryptAesKey = "1";
        this.sign = "1";
    }


    @lombok.Data
    public class data {
        public String creditOrderNo;

        public data(String CreditOrderNo) {
            this.creditOrderNo = CreditOrderNo;
        }
    }

}


