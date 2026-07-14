package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.AssetDisposeBatchController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class AssetDisposeBatchControllerQueryRo extends AssetDisposeBatchController {

    @Body
    public String assignExpireDate;
    @Body
    public String assetPkgNo;
    @Body
    public String keyValue;
    @Body
    public String assignBatchNo;
    @Body
    public Integer pageSize;
    @Body
    public String disposerCode;
    @Body
    public String keyWord;
    @Body
    public List<?> assetPkgNos;
    @Body
    public Integer current;
    @Body
    public String assetProviderCode;
    @Body
    public List<?> statusList;
    @Body
    public String projectCode;
    @Body
    public String startTime;
    @Body
    public String endTime;
    @Body
    public String assetDisposerCode;

   static {
      DenpendencesProcessor.roDendenpence(AssetDisposeBatchControllerQueryRo.class);
   }
}


