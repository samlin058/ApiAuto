package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.BackDoorContoller;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class BackDoorContollerUpdateLoanRo extends BackDoorContoller {

    static {
        DenpendencesProcessor.roDendenpence(BackDoorContollerUpdateLoanRo.class);
    }

    @Body
    public Integer orderId;
    @Body
    public String status = "LOAN_SUCCESS";

    public BackDoorContollerUpdateLoanRo(Integer orderId){
        this.orderId= orderId;
    }


}


