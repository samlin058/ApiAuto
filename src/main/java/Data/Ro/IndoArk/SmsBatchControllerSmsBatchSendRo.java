package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.SmsBatchController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;
import java.util.List;


@lombok.Data
public class SmsBatchControllerSmsBatchSendRo extends SmsBatchController {

    @Body
    public List<?> agentIds;
    @Body
    public Boolean loanTagsNull;
    @Body
    public List<?> caseStatus;
    @Body
    public List<?> loanTags;
    @Body
    public Integer templateId;
    @Body
    public Boolean allLoan;
    @Body
    public Integer loanSourceId;
    @Body
    public String sendTime;
    @Body
    public String tagCondition;
    @Body
    public Object smsOption;
    @Body
    public Object callOption;
    @Body
    public List<?> importBatchIds;
    @Body
    public String sendMode;

   static {
      DenpendencesProcessor.roDendenpence(SmsBatchControllerSmsBatchSendRo.class);
   }
}


