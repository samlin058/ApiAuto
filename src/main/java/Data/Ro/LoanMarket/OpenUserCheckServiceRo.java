package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.Open;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Util.Universal.JsonHandler;
import Service.Util.Universal.MD5Handler;
import Service.Util.Universal.StringHandler;
import Service.Util.Universal.TimestampHandler;

@lombok.Data
public class OpenUserCheckServiceRo extends Open {
    @Body
    public String channelCode;
    @Body
    public String reqid;
    @Body
    public String data;
    @Body
    public Long timestamp;
    @Body
    public String encryptAesKey;
    @Body
    public String sign;

    static {
        DenpendencesProcessor.roDendenpence(Data.Ro.LoanMarket.OpenUserCheckServiceRo.class);
    }


    /**
     * 指定用户进件
     *
     * @param mobile 手机号
     * @param idNo   身份证号
     */
    public OpenUserCheckServiceRo(String channelCode, String mobile, String idNo ,String name) {
        this.channelCode = channelCode;
        this.reqid = channelCode + TimestampHandler.getCurrentTimestamp();
        if (!StringHandler.isEmpty(mobile) && !StringHandler.isEmpty(idNo)) {
            this.data = JsonHandler.objectChange2JsonStr(new OpenUserCheckService(mobile, idNo,name));
        }
        this.timestamp = TimestampHandler.getCurrentTimestamp();
        this.encryptAesKey = "1";
        this.sign = "1";
    }

    @lombok.Data
    public class OpenUserCheckService {
        public Object mobileMd5;
        public String idNoMd5;
        public String nameMd5;

        public OpenUserCheckService(String mobile, String idNo,String name) {
            this.mobileMd5 = MD5Handler.getMD5(mobile);
            this.idNoMd5 = MD5Handler.getMD5(idNo);
            this.nameMd5 = MD5Handler.getMD5(name);
        }
    }
}




