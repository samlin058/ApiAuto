package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.CardController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class CardControllerBindInfoRo extends CardController {

   static {
      DenpendencesProcessor.roDendenpence(CardControllerBindInfoRo.class);
   }

    @Param
    public String productCode;
    @Param
    public String cardBindType;


}


