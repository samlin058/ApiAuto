package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.PreAssetPkgBatchController;
import Service.Annotations.AnnotationLibrary.FileBody;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class PreAssetPkgBatchControllerUploadFileRo extends PreAssetPkgBatchController {

    @FileBody
    public String file;

    public PreAssetPkgBatchControllerUploadFileRo(String file) {
        this.file = file;
    }

    static {
        DenpendencesProcessor.roDendenpence(PreAssetPkgBatchControllerUploadFileRo.class);
    }
}


