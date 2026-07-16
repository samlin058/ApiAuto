package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.ChannelController;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class ChannelControllerListRo extends ChannelController {

    static {
        DenpendencesProcessor.roDendenpence(ChannelControllerListRo.class);
    }

    @Param
    public String channelCode;
    @Param
    public String channelName;
    @Param
    public String status;
    @Param
    public String appName;
    @Param
    public String isSelfOperated;
    @Param
    public String current;
    @Param
    public String pageSize;


    public ChannelControllerListRo(String channelCode) {
        this.channelCode = channelCode;
    }
}


