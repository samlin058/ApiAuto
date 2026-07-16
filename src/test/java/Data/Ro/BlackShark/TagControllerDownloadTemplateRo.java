package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.TagController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class TagControllerDownloadTemplateRo extends TagController {

    @Param
    public String templateCode;

   static {
      DenpendencesProcessor.roDendenpence(TagControllerDownloadTemplateRo.class);
   }
}


