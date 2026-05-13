package Data.Ro.BlackShark;

import Data.Apis.BlackShark.PreAssetPkgBatchController;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class PreAssetPkgBatchControllerQueryLastRuleConfRo extends PreAssetPkgBatchController {

    @Param
    public String pkgBaseId;

    static {
        DenpendencesProcessor.roDendenpence(PreAssetPkgBatchControllerQueryLastRuleConfRo.class);
    }

    public PreAssetPkgBatchControllerQueryLastRuleConfRo(String pkgBaseId) {
        this.pkgBaseId = pkgBaseId;
    }
}


