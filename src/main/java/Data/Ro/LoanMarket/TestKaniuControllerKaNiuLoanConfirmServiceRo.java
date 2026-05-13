package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.TestKaniuController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class TestKaniuControllerKaNiuLoanConfirmServiceRo extends TestKaniuController {

    @Body
    public Integer loanTerm=12;
    @Body
    public String loanUse="装修贷款";
    @Body
    public String orderId;
    @Body
    public String userId;
    @Body
    public String loanId;
    @Body
    public Integer loanAmount=4000;
    @Body
    public String channelCode = "KN-MC";
    @Body
    public Integer loanTermUnit=2;
    @Body
    public String fromH5="http://www.baidu.com";

    static {
        DenpendencesProcessor.roDendenpence(TestKaniuControllerKaNiuLoanConfirmServiceRo.class);
    }

    public TestKaniuControllerKaNiuLoanConfirmServiceRo(String userId, String orderId, String loanId) {
        this.userId = userId;
        this.orderId = orderId;
        this.loanId = loanId;
    }

}


