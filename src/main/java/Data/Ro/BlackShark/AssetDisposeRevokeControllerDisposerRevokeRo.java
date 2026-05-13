package Data.Ro.BlackShark;

import Application.DataFactory.BlackShark.convertToId;
import Data.Apis.BlackShark.AssetDisposeRevokeController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class AssetDisposeRevokeControllerDisposerRevokeRo extends AssetDisposeRevokeController {

    @Body
    public String disposerCode;

   static {
      DenpendencesProcessor.roDendenpence(AssetDisposeRevokeControllerDisposerRevokeRo.class);
   }

    public AssetDisposeRevokeControllerDisposerRevokeRo(String disposerName) {
        this.disposerCode = (String) convertToId.getDisposerId(disposerName,"disposerCode");
    }

}


