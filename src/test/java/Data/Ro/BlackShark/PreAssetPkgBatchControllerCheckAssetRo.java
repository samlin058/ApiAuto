package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.PreAssetPkgBatchController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationLibrary.Dependence;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Core.TestContext;
import Service.Util.Universal.JsonHandler;

@Dependence(
        project = "blackShark", Class = "PreAssetManualReviewController", method = "queryBatchList", param ="{\"checkStatusList\":[\"UNCHECK\"],\"current\":1,\"pageSize\":10}",keys = "id_PreAssetPkgBatchControllerCheckAssetRo"
)
@lombok.Data
public class PreAssetPkgBatchControllerCheckAssetRo extends PreAssetPkgBatchController {

    @Body
    public String statusValue = "CHECK_PASSED";//默认通过
    @Body
    public String remark;
    @Body
    public String id = firstId();

    static {
        DenpendencesProcessor.roDendenpence(PreAssetPkgBatchControllerCheckAssetRo.class);
    }

    /**
     * 第一个批次id
     * @return batchId
     */
    private String firstId() {
        return (String) JsonHandler.readValueByJsonPath("$.data.data[0].batchId", TestContext.getCacheDataAsString("id_PreAssetPkgBatchControllerCheckAssetRo"));
    }

}


