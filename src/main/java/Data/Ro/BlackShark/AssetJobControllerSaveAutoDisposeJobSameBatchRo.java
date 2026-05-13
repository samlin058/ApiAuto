package Data.Ro.BlackShark;

import Data.Apis.BlackShark.AssetJobController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class AssetJobControllerSaveAutoDisposeJobSameBatchRo extends AssetJobController {

    @Body
    public String scheduleTime;
    @Body
    public String scheduleDate;
    @Body
    public List<?> pkgList;

   static {
      DenpendencesProcessor.roDendenpence(AssetJobControllerSaveAutoDisposeJobSameBatchRo.class);
   }
}


