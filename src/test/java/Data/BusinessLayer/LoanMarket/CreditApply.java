package Data.BusinessLayer.LoanMarket;

import Data.ApisLayer.LoanMarket.Open;
import Data.Ro.LoanMarket.OpenCreditApplyServiceRo;
import Data.Ro.LoanMarket.OpenUserCheckServiceRo;

public class CreditApply {

     static Open open =new Open();

    /**
     * OpenApi标准接口流程授信
     * @return
     */
    public static String openCreditApply(OpenUserCheckServiceRo userInfo,OpenCreditApplyServiceRo creditInfo){
        String result = CheckUser.openCheck(userInfo);
        return open.creditApplyService(creditInfo);
    }
}
