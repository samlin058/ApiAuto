package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.InteractionController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class InteractionControllerExportMultipleDebtInteractionRo extends InteractionController {

    @Param
    public String remark;
    @Param
    public String recallBeginDate;
    @Param
    public String recallEndDate;
    @Param
    public String account;
    @Param
    public String current;
    @Param
    public String pageSize;
    @Param
    public String loanId;
    @Param
    public String interactionsType;
    @Param
    public String conclusion;
    @Param
    public String[] types;

   static {
      DenpendencesProcessor.roDendenpence(InteractionControllerExportMultipleDebtInteractionRo.class);
   }
}


