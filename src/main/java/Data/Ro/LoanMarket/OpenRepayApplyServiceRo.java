package Data.Ro.LoanMarket;

import Application.DataFactory.LoanMarket.OpenApiScenario;
import Data.ApisLayer.LoanMarket.Open;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Util.Universal.JsonHandler;
import Service.Util.Universal.StringHandler;
import Service.Util.Universal.TimestampHandler;


@lombok.Data
public class OpenRepayApplyServiceRo extends Open {

    static {
        DenpendencesProcessor.roDendenpence(OpenRepayApplyServiceRo.class);
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

    public OpenRepayApplyServiceRo(String channelCode, String loanOrderNo, String repayOrderNo, OpenApiScenario.OpenRepayTypeEnum repayType, String periods, String smsCode) {
        this.channelCode = channelCode;
        this.reqid = channelCode + TimestampHandler.getCurrentTimestamp();
        if (!StringHandler.isEmpty(loanOrderNo)) {
            if (StringHandler.isEmpty(smsCode)) {
                this.data = JsonHandler.objectChange2JsonStr(new OpenRepayApplyServiceRo.data(loanOrderNo, repayOrderNo, repayType.getCode(), periods, "888888"));
            }
            this.data = JsonHandler.objectChange2JsonStr(new OpenRepayApplyServiceRo.data(loanOrderNo, repayOrderNo, repayType.getCode(), periods, smsCode));
        }
        this.timestamp = TimestampHandler.getCurrentTimestamp();
        this.encryptAesKey = "1";
        this.sign = "1";
    }

    @lombok.Data
    public class data {
        public String loanOrderNo;
        public String repayOrderNo;
        public String repayType;
        public String periods;
        public String smsCode;

        public data(String loanOrderNo, String repayOrderNo, String repayType, String periods, String smsCode) {
            this.loanOrderNo = loanOrderNo;
            this.repayOrderNo = repayOrderNo;
            this.repayType = repayType;
            this.periods = periods;
            this.smsCode = smsCode;
        }
    }
}


