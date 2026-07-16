package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.AssetPkgAnalysisController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssetPkgAnalysisControllerAssetPkgTransformLineChartRo extends AssetPkgAnalysisController {

    @Param
    public String dateType;
    @Param
    public String beginDate;
    @Param
    public String endDate;
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
      DenpendencesProcessor.roDendenpence(AssetPkgAnalysisControllerAssetPkgTransformLineChartRo.class);
   }
}


