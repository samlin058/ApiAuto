package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.ExternalRepayController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class ExternalRepayControllerPlanRollBackRo extends ExternalRepayController {

    @Body
    public String orgCode;
    @Body
    public String rollbackDesc;
    @Body
    public String disposeBatchNo;
    @Body
    public List<?> rollbackRepayPlanList;
    @Body
    public String reqSerialNo;
    @Body
    public String bizAssetId;
    @Body
    public String bizTime;
    @Body
    public List<?> cancelEntryReqSerialNoList;

   static {
      DenpendencesProcessor.roDendenpence(ExternalRepayControllerPlanRollBackRo.class);
   }
}


