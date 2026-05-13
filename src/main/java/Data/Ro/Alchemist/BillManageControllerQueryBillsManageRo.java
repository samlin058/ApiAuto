package Data.Ro.Alchemist;

import Data.Apis.Alchemist.BillManageController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class BillManageControllerQueryBillsManageRo extends BillManageController {

    @Param
    public Integer taskId;
    @Param
    public String assignStartTime;
    @Param
    public String assignEndTime;
    @Param
    public String approveStartTime;
    @Param
    public String approveEndTime;
    @Param
    public String applyStartTime;
    @Param
    public String applyEndTime;
    @Param
    public String interactionStartTime;
    @Param
    public String interactionEndTime;
    @Param
    public String realName;
    @Param
    public String mobile;
    @Param
    public String applicationId;
    @Param
    public String idNo;
    @Param
    public String account;
    @Param
    public String interactionConclusion;
    @Param
    public String applicationStatus;
    @Param
    public String smsStatus;
    @Param
    public String callStatus;
    @Param
    public String billTagCodes;
    @Param
    public String unOperatingStartTime;
    @Param
    public String assignStatus;
    @Param
    public String settleStatus;
    @Param
    public String active;
    @Param
    public String current;
    @Param
    public String pageSize;
    @Param
    public String callNumMin;
    @Param
    public String callNumMax;
    @Param
    public String registerStartTime;
    @Param
    public String registerEndTime;
    @Param
    public String inletStartTime;
    @Param
    public String inletEndTime;

   static {
      DenpendencesProcessor.roDendenpence(BillManageControllerQueryBillsManageRo.class);
   }
}


