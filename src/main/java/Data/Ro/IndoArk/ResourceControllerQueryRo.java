package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.ResourceController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class ResourceControllerQueryRo extends ResourceController {

    @Param
    public String appCode;
    @Param
    public String name;
    @Param
    public String Code;
    @Param
    public String status;
    @Param
    public String level;
    @Param
    public String resourceType;
    @Param
    public String parentCode;
    @Param
    public String limitCodes;

   static {
      DenpendencesProcessor.roDendenpence(ResourceControllerQueryRo.class);
   }
}


