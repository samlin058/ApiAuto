package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.ReservedController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class ReservedControllerQueryReservedTaskRo extends ReservedController {

    @Param
    public String customerName;
    @Param
    public Integer superiorAgentId;
    @Param
    public Integer caseAgentId;
    @Param
    public String beginDate;
    @Param
    public String endDate;
    @Param
    public Integer pageSize;
    @Param
    public Integer current;

   static {
      DenpendencesProcessor.roDendenpence(ReservedControllerQueryReservedTaskRo.class);
   }
}


