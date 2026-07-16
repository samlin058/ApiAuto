package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.GateWayController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class GateWayControllerIsChildRo extends GateWayController {

    @Param
    public String appCode;
    @Param
    public String parent;
    @Param
    public String child;

   static {
      DenpendencesProcessor.roDendenpence(GateWayControllerIsChildRo.class);
   }
}


