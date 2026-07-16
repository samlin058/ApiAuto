package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.CreditController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class CreditControllerGetMethodNameRo extends CreditController {

    static {
        DenpendencesProcessor.roDendenpence(CreditControllerGetMethodNameRo.class);
    }

    @Param
    public String userName;
    @Param
    public String idNo;
    @Param
    public String mobile;
    @Param
    public String channelCode;
    @Param
    public String partnerCode;
    @Param
    public String userId;
    @Param
    public String status;
    @Param
    public String creditStartTime;
    @Param
    public String creditEndTime;
    @Param
    public String channelRequestId;
    @Param
    public String partnerRequestId;
    @Param
    public String partnerApplyNo;
    @Param
    public String current;
    @Param
    public String pageSize;


}


