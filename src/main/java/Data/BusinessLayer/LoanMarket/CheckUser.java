package Data.BusinessLayer.LoanMarket;

import Data.ApisLayer.LoanMarket.Open;
import Data.Ro.LoanMarket.OpenUserCheckServiceRo;

public class CheckUser {
       static Open open =new Open();

    /**
     * OpenApi标准接口流程撞库
     * @return
     */
    public static String openCheck(OpenUserCheckServiceRo UserInfo){
          String result = open.userCheckService(UserInfo);
          return result;
    }


}
