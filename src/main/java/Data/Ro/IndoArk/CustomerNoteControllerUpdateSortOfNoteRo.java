package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.CustomerNoteController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class CustomerNoteControllerUpdateSortOfNoteRo extends CustomerNoteController {

    @Param
    public String id;
    @Param
    public String noteId;

   static {
      DenpendencesProcessor.roDendenpence(CustomerNoteControllerUpdateSortOfNoteRo.class);
   }
}


