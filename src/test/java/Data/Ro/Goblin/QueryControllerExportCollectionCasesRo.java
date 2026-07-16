package Data.Ro.Goblin;

import Data.ApisLayer.Goblin.QueryController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class QueryControllerExportCollectionCasesRo extends QueryController {

    @Param
    public String[] repaymentsStatus;
    @Param
    public String[] flowRatePhases;
    @Param
    public String applicationId;

   static {
      DenpendencesProcessor.roDendenpence(QueryControllerExportCollectionCasesRo.class);
   }
}


