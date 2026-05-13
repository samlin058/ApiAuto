package Data.Ro.LoanMarket;

import Application.DataFactory.LoanMarket.OpenApiScenario;
import Data.Apis.LoanMarket.Open;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Util.Universal.JsonHandler;
import Service.Util.Universal.StringHandler;
import Service.Util.Universal.TimestampHandler;


@lombok.Data
public class OpenRepayTrialServiceRo extends Open {
    static {
        DenpendencesProcessor.roDendenpence(OpenRepayTrialServiceRo.class);
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

    public OpenRepayTrialServiceRo(String channelCode, String loanOrderNo, OpenApiScenario.OpenRepayTypeEnum repayType, String periods) {
        this.channelCode = channelCode;
        this.reqid = channelCode + TimestampHandler.getCurrentTimestamp();
        if (!StringHandler.isEmpty(loanOrderNo)) {
            this.data = JsonHandler.objectChange2JsonStr(new OpenRepayTrialServiceRo.data(loanOrderNo, repayType.getCode(), periods));
        }
        this.timestamp = TimestampHandler.getCurrentTimestamp();
        this.encryptAesKey = "1";
        this.sign = "1";
    }

    @lombok.Data
    public class data {
        public String loanOrderNo;
        public String repayType;
        public String periods;

        public data(String loanOrderNo, String repayType, String periods) {
            this.loanOrderNo = loanOrderNo;
            this.repayType = repayType;
            this.periods = periods;
        }
    }

}


