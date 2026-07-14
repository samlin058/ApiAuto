package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.QueryController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class QueryControllerGetQueryAssignmentsAmountRo extends QueryController {

    @Param
    public String[] agentIds;
    @Param
    public String realName;
    @Param
    public String phone;
    @Param
    public String contractNumber;
    @Param
    public String idNo;
    @Param
    public String[] interactionConclusions;
    @Param
    public String assignmentBeginDate;
    @Param
    public String assignmentEndDate;
    @Param
    public String agentRule;
    @Param
    public String[] loanTag;
    @Param
    public String[] customerTags;
    @Param
    public String withdrawDateStart;
    @Param
    public String withdrawDateEnd;

   static {
      DenpendencesProcessor.roDendenpence(QueryControllerGetQueryAssignmentsAmountRo.class);
   }
}


