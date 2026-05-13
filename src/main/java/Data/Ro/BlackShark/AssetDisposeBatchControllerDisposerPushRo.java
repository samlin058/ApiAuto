package Data.Ro.BlackShark;

import Application.DataFactory.BlackShark.convertToId;
import Data.Apis.BlackShark.AssetDisposeBatchController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class AssetDisposeBatchControllerDisposerPushRo extends AssetDisposeBatchController {

    @Body
    public String disposerCode;

    static {
        DenpendencesProcessor.roDendenpence(AssetDisposeBatchControllerDisposerPushRo.class);
    }

    public AssetDisposeBatchControllerDisposerPushRo(String disposerName) {
        this.disposerCode = (String) convertToId.getDisposerId(disposerName, "disposerCode");
    }
}


