package Data.Ro.Alchemist;

import Data.ApisLayer.Alchemist.OperationController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class OperationControllerPageRo extends OperationController {

    @Body
    public String fileName;
    @Body
    public String endDate;
    @Body
    public Integer pageIndex;
    @Body
    public String channel;
    @Body
    public Integer pageSize;
    @Body
    public String startDate;
    @Body
    public String status;

   static {
      DenpendencesProcessor.roDendenpence(OperationControllerPageRo.class);
   }
}


