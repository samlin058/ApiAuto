package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.CustomerNoteController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;


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


