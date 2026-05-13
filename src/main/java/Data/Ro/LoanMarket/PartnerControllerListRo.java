package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.PartnerController;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class PartnerControllerListRo extends PartnerController {

    static {
        DenpendencesProcessor.roDendenpence(PartnerControllerListRo.class);
    }

    @Param
    public String partnerCode;
    @Param
    public String partnerName;
    @Param
    public String status;
    @Param
    public Integer current=1;
    @Param
    public Integer pageSize=100;

}


