package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.AssessmentController;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class AssessmentControllerResultRo extends AssessmentController {

    static {
        DenpendencesProcessor.roDendenpence(AssessmentControllerResultRo.class);
    }

    @Param
    public Integer id;


    public AssessmentControllerResultRo(Integer id) {
        this.id = id;
    }
}


