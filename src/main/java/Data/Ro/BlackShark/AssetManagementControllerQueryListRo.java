package Data.Ro.BlackShark;

import Data.Apis.BlackShark.AssetManagementController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class AssetManagementControllerQueryListRo extends AssetManagementController {

    @Body
    public Integer current;
    @Body
    public List<?> tagCode;
    @Body
    public Integer pageSize;
    @Body
    public String tagLogic;

   static {
      DenpendencesProcessor.roDendenpence(AssetManagementControllerQueryListRo.class);
   }
}


