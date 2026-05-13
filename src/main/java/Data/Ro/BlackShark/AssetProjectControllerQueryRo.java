package Data.Ro.BlackShark;

import Data.Apis.BlackShark.AssetProjectController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class AssetProjectControllerQueryRo extends AssetProjectController {

    @Body
    public String suspend;
    @Body
    public Integer current;
    @Body
    public String partner;
    @Body
    public List<?> processStateList;
    @Body
    public String projectTypeCode;
    @Body
    public Integer pageSize;
    @Body
    public String projectId;
    @Body
    public String status;

   static {
      DenpendencesProcessor.roDendenpence(AssetProjectControllerQueryRo.class);
   }
}


