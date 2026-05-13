package Data.Ro.BlackShark;

import Data.Apis.BlackShark.ProductionCapacityController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class ProductionCapacityControllerConnectRateRankingRo extends ProductionCapacityController {

    @Param
    public String beginDate;
    @Param
    public String endDate;
    @Param
    public String dateType;
    @Param
    public String current;
    @Param
    public String pageSize;
    @Param
    public String year;

   static {
      DenpendencesProcessor.roDendenpence(ProductionCapacityControllerConnectRateRankingRo.class);
   }
}


