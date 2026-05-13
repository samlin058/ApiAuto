package Data.Ro.IndoArk;

import Data.Apis.IndoArk.InteractionController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


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


