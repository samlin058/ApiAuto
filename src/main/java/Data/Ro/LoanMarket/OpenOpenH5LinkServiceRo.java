package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.Open;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Util.Universal.JsonHandler;
import Service.Util.Universal.StringHandler;
import Service.Util.Universal.TimestampHandler;


@lombok.Data
public class OpenOpenH5LinkServiceRo extends Open {

    static {
        DenpendencesProcessor.roDendenpence(OpenOpenH5LinkServiceRo.class);
    }

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


    public OpenOpenH5LinkServiceRo(String channelCode, String creditOrderNo, String returnUrl) {
        this.channelCode = channelCode;
        this.reqid = channelCode + TimestampHandler.getCurrentTimestamp();
        if (!StringHandler.isEmpty(creditOrderNo)) {
            this.data = JsonHandler.objectChange2JsonStr(new OpenOpenH5LinkServiceRo.data(creditOrderNo, returnUrl));
        }
        this.timestamp = TimestampHandler.getCurrentTimestamp();
        this.encryptAesKey = "1";
        this.sign = "1";
    }

    @lombok.Data
    public class data {
        public String type ="LOAN";
        public String creditOrderNo;
        public String returnUrl;


        public data(String creditOrderNo, String returnUrl) {
            this.creditOrderNo = creditOrderNo;
            if (StringHandler.isEmpty(returnUrl)){
                this.returnUrl = "https://ftmatrix-h5.ngrok.wacaiyun.com/xyj/home";
            }else {
                this.returnUrl = returnUrl;
            }
        }
    }

}


