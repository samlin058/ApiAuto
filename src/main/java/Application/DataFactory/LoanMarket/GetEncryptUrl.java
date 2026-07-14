package Application.DataFactory.LoanMarket;

import Data.ApisLayer.LoanMarket.BackDoorContoller;
import Data.Ro.LoanMarket.BackDoorContollerEncryptRo;
import Service.Util.Universal.JsonHandler;

public class GetEncryptUrl {
    /**
     * ZY_T3_PASSENGER、ZY_T3_DRIVER
     *
     * @param phone 手机号
     */
    public static String getT3DriverUrl(String phone) {
        BackDoorContoller backDoorContoller = new BackDoorContoller();
        String result = backDoorContoller.encrypt(new BackDoorContollerEncryptRo(phone, "ZY_T3"));
        String a = JsonHandler.readStringByJsonPath("$.data", result);
        String url = "http://lumos-h5-torch.ngrok.wacaiyun.com/lumos/home?channelCode=ZY_T3&jointToken=" + a;
        System.out.println(url);
        return url;
    }

    public static void main(String[] args) {
        getT3DriverUrl("13120250701");
    }


}
