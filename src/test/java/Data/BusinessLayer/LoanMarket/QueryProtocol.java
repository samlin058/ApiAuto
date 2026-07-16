package Data.BusinessLayer.LoanMarket;

import Data.ApisLayer.LoanMarket.Open;
import Data.Ro.LoanMarket.OpenAgreementServiceRo;

public class QueryProtocol {
    static Open open = new Open();

    /**
     * OpenApi标准接口流程-查询协议
     * @param ro
     * @return
     */
    public static String openApiQueryProtocol(OpenAgreementServiceRo ro){
        return open.agreementService(ro);
    }
}
