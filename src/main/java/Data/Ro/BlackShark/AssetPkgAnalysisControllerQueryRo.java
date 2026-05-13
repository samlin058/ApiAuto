package Data.Ro.BlackShark;

import Data.Apis.BlackShark.AssetPkgAnalysisController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssetPkgAnalysisControllerQueryRo extends AssetPkgAnalysisController {

    @Param
    public String assetPkgNo;
    @Param
    public String assetProviderCode;
    @Param
    public String disposerCode;
    @Param
    public String assignBatchNo;
    @Param
    public String projectCode;

   static {
      DenpendencesProcessor.roDendenpence(AssetPkgAnalysisControllerQueryRo.class);
   }
}


