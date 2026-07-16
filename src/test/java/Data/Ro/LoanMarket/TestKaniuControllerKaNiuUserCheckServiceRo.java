package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.TestKaniuController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Util.Universal.MD5Handler;
import Service.Util.Universal.TimestampHandler;


@lombok.Data
public class TestKaniuControllerKaNiuUserCheckServiceRo extends TestKaniuController {

    @Body
    public String name;
    @Body
    public String type = "2";
    @Body
    public String idNo;
    @Body
    public String phoneNo;
    @Body
    public String channelCode;
    @Body
    public Long timestamp;

    static {
        DenpendencesProcessor.roDendenpence(TestKaniuControllerKaNiuUserCheckServiceRo.class);
    }

    public static TestKaniuControllerKaNiuUserCheckServiceRo changeRequestRo(String phoneNo, String idNo, String name) {
        TestKaniuControllerKaNiuUserCheckServiceRo data = new TestKaniuControllerKaNiuUserCheckServiceRo();
        data.setChannelCode("KN-MC");
        data.setPhoneNo(MD5Handler.getMD5(phoneNo));
        data.setIdNo(MD5Handler.getMD5(idNo));
        data.setName(name);
        data.setTimestamp(TimestampHandler.getCurrentTimestamp());
        return data;
    }
}


