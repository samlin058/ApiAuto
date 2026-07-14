package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.LoanImportTemplateController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class LoanImportTemplateControllerAddLoanImportTemplateRo extends LoanImportTemplateController {

    @Param
    public String name;
    @Param
    public String createdTimeStart;
    @Param
    public String createdTimeEnd;
    @Param
    public String createdBy;
    @Param
    public Integer active;
    @Param
    public Integer current;
    @Param
    public Integer pageSize;

   static {
      DenpendencesProcessor.roDendenpence(LoanImportTemplateControllerAddLoanImportTemplateRo.class);
   }
}


