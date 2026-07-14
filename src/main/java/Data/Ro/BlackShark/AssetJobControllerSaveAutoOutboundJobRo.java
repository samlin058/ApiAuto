package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.AssetJobController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class AssetJobControllerSaveAutoOutboundJobRo extends AssetJobController {

    @Body
    public String period;
    @Body
    public List<?> assetPkgNos;
    @Body
    public Integer redialTimes;
    @Body
    public Boolean redial;
    @Body
    public Integer redialInterval;
    @Body
    public List<?> scheduleTimes;

   static {
      DenpendencesProcessor.roDendenpence(AssetJobControllerSaveAutoOutboundJobRo.class);
   }
}


