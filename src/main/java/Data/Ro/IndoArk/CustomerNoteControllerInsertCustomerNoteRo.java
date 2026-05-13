package Data.Ro.IndoArk;

import Data.Apis.IndoArk.CustomerNoteController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class CustomerNoteControllerInsertCustomerNoteRo extends CustomerNoteController {

    @Param
    public String id;
    @Body
    public String note;

   static {
      DenpendencesProcessor.roDendenpence(CustomerNoteControllerInsertCustomerNoteRo.class);
   }
}


