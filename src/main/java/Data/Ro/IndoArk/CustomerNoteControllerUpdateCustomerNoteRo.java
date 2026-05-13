package Data.Ro.IndoArk;

import Data.Apis.IndoArk.CustomerNoteController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class CustomerNoteControllerUpdateCustomerNoteRo extends CustomerNoteController {

    @Param
    public String id;
    @Param
    public String noteId;
    @Body
    public String note;

   static {
      DenpendencesProcessor.roDendenpence(CustomerNoteControllerUpdateCustomerNoteRo.class);
   }
}


