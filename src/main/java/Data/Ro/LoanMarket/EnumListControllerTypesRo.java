package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.EnumListController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class EnumListControllerTypesRo extends EnumListController {

   static {
      DenpendencesProcessor.roDendenpence(EnumListControllerTypesRo.class);
   }

    @Param
    public String uid;


}


