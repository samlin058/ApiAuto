package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.PreAssetController;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class PreAssetControllerGetAssetPackageListRo extends PreAssetController {

    @Param
    public String current = "1";
    @Param
    public String pageSize = "10";
    @Param
    public String assetType;
    @Param
    public String partnerCode;
    @Param
    public String overdueDays;
    @Param
    public String assetPackageRating;
    @Param
    public String org;
    @Param
    public String assetPkgNo;
    @Param
    public String assetImportBeginTime;
    @Param
    public String assetImportEndTime;
    @Param
    public String beginRemainingPrincipal;
    @Param
    public String endRemainingPrincipal;
    @Param
    public String beginAverageAmt;
    @Param
    public String endAverageAmt;
    @Param
    public String beginAssetResidualValue;
    @Param
    public String endAssetResidualValue;

    static {
        DenpendencesProcessor.roDendenpence(PreAssetControllerGetAssetPackageListRo.class);
    }

}


