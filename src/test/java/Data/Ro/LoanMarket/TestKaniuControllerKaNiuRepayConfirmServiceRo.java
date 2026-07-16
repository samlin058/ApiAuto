package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.TestKaniuController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class TestKaniuControllerKaNiuRepayConfirmServiceRo extends TestKaniuController {

    @Body
    public String orderId;
    @Body
    public String repayId;
    @Body
    public String userId;
    @Body
    public String loanId;
    @Body
    public String formH5;
    @Body
    public String channelCode;

   static {
      DenpendencesProcessor.roDendenpence(TestKaniuControllerKaNiuRepayConfirmServiceRo.class);
   }
}


