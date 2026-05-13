package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.AssessmentController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;

import java.util.HashMap;


@lombok.Data
public class AssessmentControllerInitRo extends AssessmentController {

    @Body
    public String productCode;

   static {
      DenpendencesProcessor.roDendenpence(AssessmentControllerInitRo.class);
   }

    public AssessmentControllerInitRo(String uid) {
        HashMap<String,String> header = new HashMap<>();
        header.put("X-Login-Uid",uid);
        this.header = header;
    }
}


