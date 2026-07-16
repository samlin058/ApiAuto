package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.AssignController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;


@lombok.Data
public class AssignControllerAssignImportRo extends AssignController {

    @FileBody
    public String file;

   static {
      DenpendencesProcessor.roDendenpence(AssignControllerAssignImportRo.class);
   }
}


