package Data.Ro.LoanMarket;

import Data.BusinessLayer.LoanMarket.H5ProcessContext;
import Data.BusinessLayer.LoanMarket.XYJH5Process;
import Data.ApisLayer.LoanMarket.OrderController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Data.Enum.LoanMarket.LoanUsageEnum;


@lombok.Data
public class OrderControllerCreateRo extends OrderController {

    static {
        DenpendencesProcessor.roDendenpence(OrderControllerCreateRo.class);
    }

    @Body
    public String productCode;//申请产品
    @Body
    public String usage;//借款用途
    @Body
    public Integer cardId; //指定借款卡
    @Body
    public Integer periods;//借款期数
    @Body
    public Integer loanAmt;//借款金额
    @Body
    public Boolean benefit = false;//是否权益


    public OrderControllerCreateRo(String productCode, LoanUsageEnum usage, Integer cardId, String periods, Integer loanAmt, Boolean benefit) {
        this.productCode = productCode;
        this.usage = usage.getValue();
        this.cardId = cardId;
        this.periods = Integer.parseInt(periods);
        this.loanAmt = loanAmt;
        this.benefit = benefit;
    }

    public OrderControllerCreateRo(String productCode, LoanUsageEnum usage, Integer cardId, String periods, Integer loanAmt) {
        this.productCode = productCode;
        this.usage = usage.getValue();
        this.cardId = cardId;
        this.periods = Integer.parseInt(periods);
        this.loanAmt = loanAmt;
    }

    public OrderControllerCreateRo(String productCode, String usage) {
        this.productCode = productCode;
        H5ProcessContext h5ProcessContext = XYJH5Process.H5processByPartner(productCode, XYJH5Process.H5ProcessStage.BIND_CARD);
        this.cardId = Integer.parseInt(h5ProcessContext.getCardId());
        this.loanAmt = Integer.parseInt(h5ProcessContext.getAvailableAmount());
        this.usage = usage;
        this.periods = Integer.parseInt(h5ProcessContext.getPeriod());
    }
}


