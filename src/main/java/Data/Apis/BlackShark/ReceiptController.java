package Data.Apis.BlackShark;
import Data.Ro.BlackShark.ReceiptControllerRepayReceiptRo;

import Data.Ro.BlackShark.ReceiptControllerRepaymentRateRankingRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class ReceiptController extends ApiBase {

    @Mapping(sub_path = "/blackShark/receipt", requestType = "get" )
    public String RepayReceipt (ReceiptControllerRepayReceiptRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/receipt/repayment/ranking", requestType = "get" )
    public String repaymentRateRanking (ReceiptControllerRepaymentRateRankingRo ro) {
    return http(ro);
    }


}



