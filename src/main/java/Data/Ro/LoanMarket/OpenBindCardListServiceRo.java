package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.Open;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Util.Universal.JsonHandler;
import Service.Util.Universal.StringHandler;
import Service.Util.Universal.TimestampHandler;


@lombok.Data
public class OpenBindCardListServiceRo extends Open {

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
        DenpendencesProcessor.roDendenpence(OpenBindCardListServiceRo.class);
    }

    public OpenBindCardListServiceRo(String channelCode, String creditOrderNo) {
        this.channelCode = channelCode;
        this.reqid = channelCode + TimestampHandler.getCurrentTimestamp();
        if (!StringHandler.isEmpty(creditOrderNo)) {
            this.data = JsonHandler.objectChange2JsonStr(new data(creditOrderNo));
        }
        this.timestamp = TimestampHandler.getCurrentTimestamp();
        this.encryptAesKey = "1";
        this.sign = "1";
    }

    @lombok.Data
    public class data {
        public String creditOrderNo;

        public data(String creditOrderNo) {
            this.creditOrderNo = creditOrderNo;
        }
    }
}


