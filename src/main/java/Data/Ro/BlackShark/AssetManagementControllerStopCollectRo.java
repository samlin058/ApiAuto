package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.AssetManagementController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssetManagementControllerStopCollectRo extends AssetManagementController {

    @Param
    public String assetProviderCode;
    @Param
    public String projectCode;
    @Param
    public String assetPkgNo;
    @Param
    public String disposerCode;
    @Param
    public String assignBatchNo;
    @Param
    public String orderNo;
    @Param
    public String assetId;
    @Param
    public String debtorName;
    @Param
    public String debtorMobile;
    @Param
    public String[] assetStatusValueList;
    @Param
    public String idNo;
    @Param
    public String[] disposeStatusValueList;
    @Param
    public String settleStart;
    @Param
    public String settleEnd;
    @Param
    public String tagCode;
    @Param
    public String caseStateList;
    @Param
    public String urgingCause;

   static {
      DenpendencesProcessor.roDendenpence(AssetManagementControllerStopCollectRo.class);
   }
}


