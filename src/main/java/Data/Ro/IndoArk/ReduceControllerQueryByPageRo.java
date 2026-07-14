package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.ReduceController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class ReduceControllerQueryByPageRo extends ReduceController {

    @Param
    public String contractNumber;
    @Param
    public String realname;
    @Param
    public Integer reduceApplyId;
    @Param
    public String idno;
    @Param
    public String mobile;
    @Param
    public String beginDate;
    @Param
    public String endDate;
    @Param
    public String statusList;
    @Param
    public String status;
    @Param
    public Integer applyAgentId;
    @Param
    public Integer superiorAgentId;
    @Param
    public Integer processId;
    @Param
    public String current;
    @Param
    public String pageSize;

   static {
      DenpendencesProcessor.roDendenpence(ReduceControllerQueryByPageRo.class);
   }
}


