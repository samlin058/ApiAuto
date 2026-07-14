package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.Open;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class OpenSupportBankServiceRo extends Open {

    @Body
    public Object data;
    @Body
    public String sign;
    @Body
    public String channelCode;
    @Body
    public String reqid;
    @Body
    public String timestamp;
    @Body
    public String encryptAesKey;

   static {
      DenpendencesProcessor.roDendenpence(OpenSupportBankServiceRo.class);
   }
}


