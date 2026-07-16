package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.RepayController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class RepayControllerApplyListRo extends RepayController {

    @Param
    public String contractNumber;
    @Param
    public String realname;
    @Param
    public Integer processId;
    @Param
    public String idno;
    @Param
    public String mobile;
    @Param
    public String beginDate;
    @Param
    public String endDate;
    @Param
    public String status;
    @Param
    public Integer applyAgentId;
    @Param
    public Integer loanSourceId;
    @Param
    public Integer current;
    @Param
    public Integer pageSize;
    @Param
    public String[] statusList;
    @Param
    public String batchNumber;

   static {
      DenpendencesProcessor.roDendenpence(RepayControllerApplyListRo.class);
   }
}


