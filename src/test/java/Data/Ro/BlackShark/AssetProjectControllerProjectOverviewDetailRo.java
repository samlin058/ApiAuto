package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.AssetProjectController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssetProjectControllerProjectOverviewDetailRo extends AssetProjectController {

    @Param
    public String projectId;
    @Param
    public String isMyProject;

   static {
      DenpendencesProcessor.roDendenpence(AssetProjectControllerProjectOverviewDetailRo.class);
   }
}


