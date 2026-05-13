package Data.Ro.BlackShark;

import Data.Apis.BlackShark.PreAssetPkgController;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class PreAssetPkgControllerQueryBaseDataRo extends PreAssetPkgController {

    @Param
    public String assetPkgNo;

    static {
        DenpendencesProcessor.roDendenpence(PreAssetPkgControllerQueryBaseDataRo.class);
    }

    public PreAssetPkgControllerQueryBaseDataRo(String assetPkgNo) {
        this.assetPkgNo = assetPkgNo;
    }
}


