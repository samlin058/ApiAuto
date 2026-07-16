package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.EnumListController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class EnumListControllerTypesRo extends EnumListController {

   static {
      DenpendencesProcessor.roDendenpence(EnumListControllerTypesRo.class);
   }

    @Param
    public String uid;


}


