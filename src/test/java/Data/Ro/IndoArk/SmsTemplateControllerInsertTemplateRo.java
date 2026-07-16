package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.SmsTemplateController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class SmsTemplateControllerInsertTemplateRo extends SmsTemplateController {

    @Param
    public Integer loanSourceId;

   static {
      DenpendencesProcessor.roDendenpence(SmsTemplateControllerInsertTemplateRo.class);
   }
}


