package Data.Ro.Alchemist;

import Data.Apis.Alchemist.BillManageController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class BillManageControllerSendBatchSmsRo extends BillManageController {

    @Body
    public String applyEndTime;
    @Body
    public String interactionEndTime;
    @Body
    public String unOperatingStartTime;
    @Body
    public Integer settleStatus;
    @Body
    public Integer interactionConclusion;
    @Body
    public Integer mobile;
    @Body
    public String approveEndTime;
    @Body
    public Integer active;
    @Body
    public Integer idNo;
    @Body
    public String applyStartTime;
    @Body
    public String realName;
    @Body
    public String interactionStartTime;
    @Body
    public Integer applicationStatus;
    @Body
    public String approveStartTime;
    @Body
    public Integer assignStatus;
    @Body
    public Integer smsStatus;
    @Body
    public String assignStartTime;
    @Body
    public Integer callStatus;
    @Body
    public Integer applicationId;
    @Body
    public List<?> billTagCodes;
    @Body
    public String assignEndTime;
    @Body
    public Integer taskId;
    @Body
    public String account;

   static {
      DenpendencesProcessor.roDendenpence(BillManageControllerSendBatchSmsRo.class);
   }
}


