package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.Open;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Util.Universal.JsonHandler;
import Service.Util.Universal.StringHandler;
import Service.Util.Universal.TimestampHandler;


@lombok.Data
public class OpenAgreementServiceRo extends Open {

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
        DenpendencesProcessor.roDendenpence(OpenAgreementServiceRo.class);
    }


    public OpenAgreementServiceRo(String channelCode, String creditOrderNo, String type, String loanNo) {
        this.channelCode = channelCode;
        this.reqid = channelCode + TimestampHandler.getCurrentTimestamp();
        if (!StringHandler.isEmpty(creditOrderNo) && !StringHandler.isEmpty(type)) {
            this.data = JsonHandler.objectChange2JsonStr(new OpenAgreementServiceRo.OpenUserCheckService(creditOrderNo, type, loanNo));
        }
        this.timestamp = TimestampHandler.getCurrentTimestamp();
        this.encryptAesKey = "1";
        this.sign = "1";
    }

    @lombok.Data
    public class OpenUserCheckService {
        public Object creditOrderNo;
        public String type;
        public String loanNo;

        public OpenUserCheckService(String creditOrderNo, String type, String loanNo) {
            this.creditOrderNo = creditOrderNo;
            this.type = type;
            this.loanNo = loanNo;
        }
    }


}


