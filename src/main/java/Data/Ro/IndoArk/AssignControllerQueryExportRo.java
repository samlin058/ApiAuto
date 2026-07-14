package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.AssignController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class AssignControllerQueryExportRo extends AssignController {

    @Param
    public String contractNumber	;
    @Param
    public String idNo;
    @Param
    public String realName;
    @Param
    public String phone;
    @Param
    public String agentIds	;
    @Param
    public String assistantAgentIds;
    @Param
    public String[] customerTags;

   static {
      DenpendencesProcessor.roDendenpence(AssignControllerQueryExportRo.class);
   }
}


