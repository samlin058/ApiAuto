package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.TagController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class TagControllerTruncateEnumRo extends TagController {

    @FileBody
    public String file;
    @Body
    public String tagCode;
    @Body
    public String assetPkgNo;
    @Body
    public String parentTagCode;

   static {
      DenpendencesProcessor.roDendenpence(TagControllerTruncateEnumRo.class);
   }
}


