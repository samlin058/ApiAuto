package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.QueryController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class QueryControllerGetQueryAssignmentsBelongToMeRo extends QueryController {

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
    public String batchNumber;
    @Param
    public Integer loanSourceId;
    @Param
    public String loanTag;
    @Param
    public String loanColor;
    @Param
    public String current;
    @Param
    public String pageSize;
    @Param
    public String sortField;
    @Param
    public String sortOrder;
    @Param
    public String[] customerTags;
    @Param
    public String withdrawDateStart;
    @Param
    public String withdrawDateEnd;

   static {
      DenpendencesProcessor.roDendenpence(QueryControllerGetQueryAssignmentsBelongToMeRo.class);
   }
}


