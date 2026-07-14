package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.ReservedController;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class ReservedControllerQueryReservedApplyRo extends ReservedController {

    @Param
    public String customerName;
    @Param
    public String approveStatus;
    @Param
    public Integer deadLine;
    @Param
    public Integer beginDate;
    @Param
    public Integer endDate;
    @Param
    public Integer pageSize;
    @Param
    public Integer current;

   static {
      DenpendencesProcessor.roDendenpence(ReservedControllerQueryReservedApplyRo.class);
   }
}


