package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.BackDoorContoller;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Util.Universal.TimestampHandler;


@lombok.Data
public class BackDoorContollerEncryptRo extends BackDoorContoller {

    @Param
    public String channelCode;
    @Param
    public String value;

    static {
        DenpendencesProcessor.roDendenpence(BackDoorContollerEncryptRo.class);
    }

    public BackDoorContollerEncryptRo(String phone, String channelCode) {
        this.channelCode = channelCode;
        Long currentTimestamp = TimestampHandler.getCurrentTimestamp();
        Long expireTimestamp = TimestampHandler.getTimestamp(2050, 5, 5, 5, 5, 5);
        if ("ZY_FL".equals(channelCode)) {
            this.value = "channelCode=ZY_FL&expireAt=" + expireTimestamp + "&mobile=" + phone + "&ts=" + currentTimestamp;
        }
        if ("ZY_T3".equals(channelCode) || "ZY_T3_DRIVER".equals(channelCode)) {
            this.value = "{\n" +
                    "    \"channelCode\": \"ZY_T3\",\n" +
                    "    \"mobile\": \"" + phone + "\",\n" +
                    "    \"userUuid\": \"915237c72bbb4ba4ae5915f111a02f256\",\n" +
                    "    \"bizType\": \"DRIVER\",\n" +
                    "    \"expireAt\": " + expireTimestamp + ",\n" +
                    "    \"ts\":" + currentTimestamp + "\n" +
                    "}";
        }
        if ("ZY_T3_PASSENGER".equals(channelCode)) {
            this.value = "{\n" +
                    "    \"channelCode\": \"ZY_T3\",\n" +
                    "    \"mobile\": \"" + phone + "\",\n" +
                    "    \"userUuid\": \"915237c72bbb4ba4ae5915f111a02f256\",\n" +
                    "    \"bizType\": \"PASSENGER\",\n" +
                    "    \"expireAt\": " + expireTimestamp + ",\n" +
                    "    \"ts\":" + currentTimestamp + "\n" +
                    "}";
        }
    }

    public static void main(String[] args) {
        BackDoorContollerEncryptRo backDoorContollerEncryptRo = new BackDoorContollerEncryptRo("13966665555", "ZY_T3");
        System.out.println(backDoorContollerEncryptRo);
    }
}


