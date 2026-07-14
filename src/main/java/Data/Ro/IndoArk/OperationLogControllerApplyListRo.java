package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.OperationLogController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class OperationLogControllerApplyListRo extends OperationLogController {

    @Param
    public String type;
    @Param
    public String operator;
    @Param
    public String beginDate;
    @Param
    public String endDate;
    @Param
    public Integer loanId;
    @Param
    public String moduleCode;
    @Param
    public String[] typeList;
    @Param
    public String customerId;
    @Param
    public Integer current;
    @Param
    public String pageSize;

   static {
      DenpendencesProcessor.roDendenpence(OperationLogControllerApplyListRo.class);
   }
}


