package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.ReservedController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class ReservedControllerExecuteApproveRo extends ReservedController {

    @Body
    public String approveStatus;
    @Body
    public String beginDate;
    @Body
    public String reservedApplyId;
    @Body
    public Number caseAgentId;
    @Body
    public Number superiorAgentId;
    @Body
    public String endDate;
    @Body
    public String mobile;
    @Body
    public String applicationId;
    @Body
    public String idNo;
    @Body
    public String customerName;
    @Body
    public String approveFeedback;

   static {
      DenpendencesProcessor.roDendenpence(ReservedControllerExecuteApproveRo.class);
   }
}


